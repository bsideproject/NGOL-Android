package com.nugu.nuguollim.ui.home

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
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
import com.nugu.nuguollim.design_system.component.NuguTemplateItem
import com.nugu.nuguollim.design_system.component.PretendardText
import com.nugu.nuguollim.design_system.theme.Black
import com.nugu.nuguollim.design_system.theme.Gray700
import com.nugu.nuguollim.design_system.theme.Primary500
import com.nugu.nuguollim.design_system.theme.pretendard
import com.nugu.nuguollim.ui.R
import com.nugu.search.component.SearchTextField
import com.nugu.search.ui.TemplateSearchEmptyScreen
import com.nugu.ui_core.StringUtil
import com.nugu.ui_core.addFocusCleaner
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

@Composable
fun HomeRoute(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
    onClickTemplate: (Template) -> Unit = {}
) {
    val activity = LocalContext.current as Activity
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    var isFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    BackHandler(true) {
        if (viewModel.isFinish) {
            activity.finishAffinity()
            exitProcess(0)
        } else {
            Toast.makeText(
                activity,
                "한번 더 누르면 앱이 종료됩니다.",
                Toast.LENGTH_SHORT
            ).show()
            viewModel.backPress()
        }
    }

    Scaffold(
        modifier = Modifier.addFocusCleaner(focusManager) { isFocused = false },
        bottomBar = { NuguBottomNavigation(navController = navController) }
    ) { innerPadding ->
        val currentTemplates = viewModel.templatePaging.collectAsLazyPagingItems()

        HomeScreen(
            modifier = Modifier.padding(innerPadding),
            templatePaging = currentTemplates,
            isFocused = isFocused,
            focusManager = focusManager,
            focusRequester = focusRequester,
            onFocusChanged = { isFocused = it },
            onSortChanged = { viewModel.setSort(it.sortText) },
            onSearchText = { viewModel.setKeyword(it) },
            onFavorite = { id, isFavorite ->
                if (isFavorite) {
                    viewModel.addFavorite(id)
                } else {
                    viewModel.removeFavorite(id)
                }
            },
            onTemplateClick = { onClickTemplate(it) },
            onRefresh = {
                focusManager.clearFocus()
                viewModel.refresh()
            }
        )
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    templatePaging: LazyPagingItems<Template> = flowOf(PagingData.from(listOf<Template>())).collectAsLazyPagingItems(),
    isFocused: Boolean = false,
    focusManager: FocusManager = LocalFocusManager.current,
    focusRequester: FocusRequester = FocusRequester(),
    onFocusChanged: (Boolean) -> Unit = {},
    onSortChanged: (TemplateSort) -> Unit = {},
    onTemplateClick: (Template) -> Unit = {},
    onSearchText: (String) -> Unit = {},
    onFavorite: (Long, Boolean) -> Unit = { _, _ -> },
    onRefresh: () -> Unit = {},
) {
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    var inputKeyword by remember { mutableStateOf("") }
    var searchKeyword by remember { mutableStateOf("") }

    val refresh: () -> Unit = {
        coroutineScope.launch {
            inputKeyword = ""
            searchKeyword = ""
            scrollState.scrollToItem(0)
            onRefresh.invoke()
        }
    }

    Column(
        modifier = modifier
    ) {
        HomeSearchBar(
            keyword = inputKeyword,
            onSearchText = { keyword ->
                inputKeyword = keyword
                searchKeyword = keyword
                onSearchText.invoke(keyword)
            },
            isFocused = isFocused,
            focusRequester = focusRequester,
            focusManager = focusManager,
            onFocusChanged = onFocusChanged,
            onClear = {
                inputKeyword = ""
                inputKeyword = ""
            },
            onValueChanged = { inputKeyword = it },
            onRefresh = refresh
        )
        HomeSearchListMenu(
            keyword = searchKeyword,
            scrollState = scrollState,
            templatePaging = templatePaging,
            onItemClicked = onTemplateClick,
            onSortChanged = onSortChanged,
            onFavorite = onFavorite,
            onRefresh = refresh,
        )
    }
}

@Composable
fun HomeSearchBar(
    modifier: Modifier = Modifier,
    keyword: String,
    isFocused: Boolean,
    focusManager: FocusManager,
    focusRequester: FocusRequester,
    onFocusChanged: (Boolean) -> Unit = {},
    onSearchText: (String) -> Unit = {},
    onRefresh: () -> Unit = {},
    onClear: () -> Unit = {},
    onValueChanged: (String) -> Unit = {}
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Primary500)
            .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_logo_top),
                contentDescription = "logo",
                modifier = Modifier
                    .width(62.dp)
                    .height(20.54.dp)
                    .clickable { onRefresh.invoke() },
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

        SearchTextField(
            value = keyword,
            isFocused = isFocused,
            placeHolder = "검색어를 입력해주세요.",
            onClear = onClear,
            onValueChange = onValueChanged,
            onSearch = { onSearchText.invoke(keyword) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus()
                    onSearchText.invoke(keyword)
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .align(Alignment.CenterHorizontally)
                .focusRequester(focusRequester)
                .onFocusChanged { onFocusChanged.invoke(it.isFocused) }
        )
    }
}

@Composable
fun HomeSearchListMenu(
    keyword: String,
    scrollState: LazyListState = rememberLazyListState(),
    modifier: Modifier = Modifier,
    templatePaging: LazyPagingItems<Template> = flowOf(PagingData.from(listOf<Template>())).collectAsLazyPagingItems(),
    onSortChanged: (TemplateSort) -> Unit = {},
    onItemClicked: (Template) -> Unit = {},
    onFavorite: (Long, Boolean) -> Unit = { _, _ -> },
    onRefresh: () -> Unit = {}
) {
    val isNotEmptyKeyword = keyword.isNotEmpty()
    var selectTitle by remember { mutableStateOf(TemplateSort.values().first().title) }
    var title by remember { mutableStateOf(TemplateSort.values().first().title) }

    title = if (isNotEmptyKeyword) {
        String.format(
            stringResource(com.nugu.search.R.string.description_search_template_title),
            keyword
        )
    } else {
        selectTitle
    }

    Column(
        modifier = modifier
            .background(Color.White)
            .padding(horizontal = 20.dp)
            .padding(top = 12.dp)
    ) {
        if (templatePaging.itemCount > 0) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Black,
                        overflow = TextOverflow.Ellipsis
                    )
                    if (isNotEmptyKeyword) {
                        PretendardText(
                            text = stringResource(com.nugu.search.R.string.description_search_template_tip),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            color = Gray700,
                            style = MaterialTheme.typography.h4
                        )
                    }
                }
                NuguDropDownMenu(
                    items = TemplateSort.values().toList(),
                    onSelectItem = { sort ->
                        if (!isNotEmptyKeyword) {
                            selectTitle = sort.title
                        }
                        onSortChanged(sort)
                    }
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn(
                state = scrollState,
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
                    if (item != null) {
                        var isFavorite by remember { mutableStateOf(item.favorite) }
                        val target = StringUtil.spannableAll(
                            text = item.target,
                            keyword = keyword,
                            style = SpanStyle(color = Primary500)
                        )
                        val label = StringUtil.spannableAll(
                            text = item.theme,
                            keyword = keyword,
                            style = SpanStyle(color = Primary500)
                        )
                        val content = StringUtil.spannableAll(
                            text = item.content,
                            keyword = keyword,
                            style = SpanStyle(color = Primary500)
                        )

                        NuguTemplateItem(
                            target = target,
                            label = label,
                            content = content,
                            onClick = { onItemClicked.invoke(item) },
                            isFavorite = isFavorite,
                            onClickFavorite = {
                                isFavorite = !isFavorite
                                onFavorite.invoke(item.id, isFavorite)
                            }
                        )
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                TemplateSearchEmptyScreen(
                    modifier = Modifier.padding(bottom = 2.dp),
                    keyword = keyword,
                    onNavigateToSearchHome = onRefresh
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