package com.nugu.nuguollim.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.common.data.model.template.TemplateSort
import com.nugu.nuguollim.design_system.component.NuguBottomNavigation
import com.nugu.nuguollim.design_system.component.NuguDropDownMenu
import com.nugu.nuguollim.design_system.component.NuguSearchTextField
import com.nugu.nuguollim.design_system.component.NuguTemplateItem
import com.nugu.nuguollim.design_system.theme.Black
import com.nugu.nuguollim.design_system.theme.Primary500
import com.nugu.nuguollim.design_system.theme.pretendard
import com.nugu.nuguollim.ui.R
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeRoute(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        bottomBar = { NuguBottomNavigation(navController = navController) }
    ) { innerPadding ->
        val currentTemplates = viewModel.templatePaging.collectAsLazyPagingItems()

        HomeScreen(
            modifier = Modifier.padding(innerPadding),
            templatePaging = currentTemplates,
            onSortChanged = { viewModel.setSort(it.sortText) },
            onSearchText = { viewModel.setKeyword(it) }
        )
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    templatePaging: LazyPagingItems<Template> = flowOf(PagingData.from(listOf<Template>())).collectAsLazyPagingItems(),
    onSortChanged: (TemplateSort) -> Unit = {},
    onTemplateClick: (Template) -> Unit = {},
    onSearchText: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {
        HomeSearchBar(
            onSearchText = onSearchText
        )
        HomeSearchListMenu(
            templatePaging = templatePaging,
            onItemClicked = onTemplateClick,
            onSortChanged = onSortChanged
        )
    }
}

@Composable
fun HomeSearchBar(
    modifier: Modifier = Modifier,
    onSearchText: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Primary500)
            .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_logo_top),
                contentDescription = "logo",
                modifier = Modifier
                    .width(62.dp)
                    .height(20.54.dp),
                tint = Color.White
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_user),
                contentDescription = "profile",
                modifier = Modifier
                    .width(19.65.dp)
                    .height(20.dp),
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(26.dp))

        Text(
            text = stringResource(id = R.string.description_home),
            fontFamily = pretendard,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(26.dp))

        NuguSearchTextField(
            modifier = Modifier.fillMaxWidth(),
            onSearchText = onSearchText
        )
    }
}

@Composable
fun HomeSearchListMenu(
    modifier: Modifier = Modifier,
    templatePaging: LazyPagingItems<Template> = flowOf(PagingData.from(listOf<Template>())).collectAsLazyPagingItems(),
    onSortChanged: (TemplateSort) -> Unit = {},
    onItemClicked: (Template) -> Unit = {}
) {
    var title by remember { mutableStateOf(TemplateSort.values().first().title) }

    Column(
        modifier = modifier
            .background(Color.White)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Black
            )
            NuguDropDownMenu(
                items = TemplateSort.values().toList(),
                onSelectItem = {
                    title = it.title
                    onSortChanged(it)
                }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            when (val state = templatePaging.loadState.refresh) {
                is LoadState.NotLoading -> Unit
                LoadState.Loading -> Loading()
                is LoadState.Error -> Unit
            }
            when (val state = templatePaging.loadState.append) {
                is LoadState.NotLoading -> Unit
                LoadState.Loading -> Loading()
                is LoadState.Error -> Unit
            }
            when (val state = templatePaging.loadState.prepend) {
                is LoadState.NotLoading -> Unit
                LoadState.Loading -> Loading()
                is LoadState.Error -> Unit
            }
            items(items = templatePaging) { item ->
                NuguTemplateItem(
                    label = item?.theme ?: "",
                    content = item?.content ?: "",
                    onClick = { item?.let(onItemClicked) }
                )
            }
        }
    }
}

private fun LazyListScope.Loading() {
    item {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun HomeScreenPreview() {
    val items = remember {
        mutableStateListOf<Template>().apply {
            repeat(50) {
                add(
                    Template(
                        id = 0,
                        content = "교수님 안녕하세요?\n" +
                                "저는 2023년도 1학기 <누구올림의 이해> 수업을 수강 중인 경제학과 정느리라고 합니다.\n" +
                                "다름이 아니라, 교수님 안녕하세요? 저는 2023년도 1학기 <누구올림의 이해> 수업을 수강 중인 경제학과 정느리라고 합니다.\n" +
                                "다름이 아니라, ",
                        theme = "문의/답변"
                    )
                )
                add(
                    Template(
                        id = 1,
                        content = "교수님 안녕하세요?\n" +
                                "저는 2023년도 1학기 <누구올림의 이해> 수업을 수강 중인 경제학과 정느리라고 합니다.\n" +
                                "다름이 아니라, 교수님 안녕하세요? 저는 2023년도 1학기 <누구올림의 이해> 수업을 수강 중인 경제학과 정느리라고 합니다.\n" +
                                "다름이 아니라, ",
                        theme = "상담 문의"
                    )
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeScreen(
            templatePaging = flowOf(PagingData.from(items)).collectAsLazyPagingItems()
        )
    }
}