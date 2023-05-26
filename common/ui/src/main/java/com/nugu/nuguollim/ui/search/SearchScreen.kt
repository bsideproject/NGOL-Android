package com.nugu.nuguollim.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.design_system.component.NuguBottomNavigation
import com.nugu.nuguollim.design_system.component.NuguToolbar
import com.nugu.nuguollim.design_system.component.NuguToolbarTitle
import com.nugu.nuguollim.design_system.theme.NuguIcons
import com.nugu.nuguollim.ui.search.target.TargetChoiceScreen
import com.nugu.nuguollim.ui.search.template.TemplateSearchRoute
import com.nugu.nuguollim.ui.search.theme.ThemeSearchRoute
import com.nugu.search.component.SearchTextField
import com.nugu.search.nav.SearchNavigation
import com.nugu.search.nav.SearchParameterData
import com.nugu.search.nav.navigateToTemplate
import com.nugu.search.nav.targetScreen
import com.nugu.search.nav.templateScreen
import com.nugu.search.nav.themeScreen
import com.nugu.ui_core.addFocusCleaner

@Composable
fun SearchRoute(
    parentNavController: NavHostController,
    onClickTemplate: (Template) -> Unit = {}
) {
    var targetId: Long? by rememberSaveable { mutableStateOf(null) }
    var themeId: Long? by rememberSaveable { mutableStateOf(null) }
    var searchNavigationState by remember { mutableStateOf<SearchNavigation>(SearchNavigation.Target) }
    val childHostController = rememberNavController()
    val systemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(Color.White)

    Scaffold(
        topBar = {
            if (searchNavigationState !is SearchNavigation.Target) {
                NuguToolbar(
                    navigation = {
                        IconButton(onClick = { childHostController.popBackStack() }) {
                            NuguIcons.BackArrow
                        }
                    },
                    title = { NuguToolbarTitle("템플릿 찾기") }
                )
            }
        },
        bottomBar = { NuguBottomNavigation(navController = parentNavController) }
    ) { innerPadding ->
        SearchScreen(
            modifier = Modifier.padding(
                start = 20.dp,
                end = 20.dp,
                bottom = innerPadding.calculateBottomPadding()
            ),
            onSearchKeyword = { keyword ->
                val data = SearchParameterData(
                    targetId = targetId,
                    themeId = themeId,
                    keyword = keyword
                )
                childHostController.navigateToTemplate(
                    data = data,
                    popUpTo = searchNavigationState.route
                )
            }
        ) {
            NavHost(
                navController = childHostController,
                startDestination = SearchNavigation.Target.route
            ) {
                targetScreen {
                    TargetChoiceScreen(childHostController) { targetId = it?.toLong() }
                    searchNavigationState = SearchNavigation.Target
                }
                themeScreen {
                    ThemeSearchRoute(
                        onThemeId = { themeId = it?.toLong() },
                        onClickTemplate = onClickTemplate
                    )
                    searchNavigationState = SearchNavigation.Theme
                }
                templateScreen {
                    TemplateSearchRoute(
                        navController = childHostController,
                        onClickTemplate = onClickTemplate
                    )
                    searchNavigationState = SearchNavigation.Template
                }
            }
        }
    }
}

@Composable
private fun SearchScreen(
    modifier: Modifier = Modifier,
    onSearchKeyword: (String) -> Unit = {},
    content: @Composable () -> Unit
) {
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    var isFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    var searchText by remember { mutableStateOf("") }

    Surface(
        modifier = modifier
            .addFocusCleaner(focusManager) { isFocused = false }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Spacer(modifier = Modifier.padding(10.dp))

            SearchTextField(
                value = searchText,
                isFocused = isFocused,
                placeHolder = "검색어를 입력해주세요.",
                onClear = { searchText = "" },
                onValueChange = { searchText = it },
                onSearch = { onSearchKeyword.invoke(searchText) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        focusManager.clearFocus()
                        onSearchKeyword.invoke(searchText)
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .align(Alignment.CenterHorizontally)
                    .focusRequester(focusRequester)
                    .onFocusChanged { isFocused = it.isFocused }
            )

            content.invoke()
        }

    }
}