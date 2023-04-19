package com.nugu.nuguollim.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.nugu.nuguollim.common.data.model.template.HomeTemplate
import com.nugu.nuguollim.design_system.component.NuguBottomNavigation
import com.nugu.nuguollim.design_system.component.NuguDropDownMenu
import com.nugu.nuguollim.design_system.component.NuguSearchTextField
import com.nugu.nuguollim.design_system.component.NuguTemplateItem
import com.nugu.nuguollim.design_system.theme.Black
import com.nugu.nuguollim.design_system.theme.Primary500
import com.nugu.nuguollim.design_system.theme.pretendard
import com.nugu.nuguollim.ui.R

@Composable
fun HomeRoute(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        bottomBar = { NuguBottomNavigation(navController = navController) }
    ) { innerPadding ->

        HomeScreen(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeTemplates: List<HomeTemplate> = listOf(),
    onTemplateClick: (HomeTemplate) -> Unit = {},
    onSearchText: (String) -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }
    var sortedKey by remember { mutableStateOf("인기순") }
    val sortedTemplates by remember {
        mutableStateOf(
            homeTemplates.toList().sortedBy { it.viewCount }
        )
    }

    Column(
        modifier = modifier
    ) {
        HomeSearchBar(
            text = searchText,
            onValueChange = { searchText = it },
            onSearchText = onSearchText
        )
        HomeSearchListMenu(
            items = sortedTemplates,
            onItemClicked = onTemplateClick,
            onSortChanged = {
                if (it != sortedKey) {
                    if (it == "인기순") {
                        sortedTemplates.sortedBy { template -> template.viewCount }
                    } else if (it == "최신순") {
                        sortedTemplates.sortedBy { template -> template.date }
                    }
                    sortedKey = it
                }
            }
        )
    }
}

@Composable
fun HomeSearchBar(
    modifier: Modifier = Modifier,
    text: String = "",
    onValueChange: (String) -> Unit = {},
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
            text = text,
            onValueChange = onValueChange,
            onSearchText = onSearchText
        )
    }
}

@Composable
fun HomeSearchListMenu(
    modifier: Modifier = Modifier,
    items: List<HomeTemplate> = listOf(),
    onSortChanged: (String) -> Unit = {},
    onItemClicked: (HomeTemplate) -> Unit = {}
) {
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
                text = stringResource(id = R.string.description_template_list),
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Black
            )
            NuguDropDownMenu(
                items = listOf("인기순", "최신순"),
                onSelectItem = { onSortChanged(it) }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items) { item ->
                NuguTemplateItem(
                    label = item.theme,
                    content = item.content,
                    onClick = { onItemClicked(item) }
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun HomeScreenPreview() {
    val items = remember {
        mutableStateListOf<HomeTemplate>().apply {
            repeat(50) {
                add(
                    HomeTemplate(
                        0,
                        "교수님 안녕하세요?\n" +
                                "저는 2023년도 1학기 <누구올림의 이해> 수업을 수강 중인 경제학과 정느리라고 합니다.\n" +
                                "다름이 아니라, 교수님 안녕하세요? 저는 2023년도 1학기 <누구올림의 이해> 수업을 수강 중인 경제학과 정느리라고 합니다.\n" +
                                "다름이 아니라, ",
                        "문의/답변"
                    )
                )
                add(
                    HomeTemplate(
                        0,
                        "교수님 안녕하세요?\n" +
                                "저는 2023년도 1학기 <누구올림의 이해> 수업을 수강 중인 경제학과 정느리라고 합니다.\n" +
                                "다름이 아니라, 교수님 안녕하세요? 저는 2023년도 1학기 <누구올림의 이해> 수업을 수강 중인 경제학과 정느리라고 합니다.\n" +
                                "다름이 아니라, ",
                        "상담 문의"
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
            homeTemplates = items
        )
    }
}