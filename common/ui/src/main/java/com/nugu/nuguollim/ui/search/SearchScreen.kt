package com.nugu.nuguollim.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.nugu.nuguollim.design_system.component.NuguBottomNavigation
import com.nugu.nuguollim.design_system.component.NuguToolbar
import com.nugu.nuguollim.design_system.component.NuguToolbarTitle
import com.nugu.nuguollim.design_system.theme.NuguIcons
import com.nugu.nuguollim.ui.search.target.TargetChoiceScreen
import com.nugu.nuguollim.ui.search.template.TemplateSearchScreen
import com.nugu.nuguollim.ui.search.theme.ThemeSearchRoute
import com.nugu.search.component.SearchTextField
import com.nugu.search.nav.SearchNavigation
import com.nugu.search.nav.targetScreen
import com.nugu.search.nav.templateScreen
import com.nugu.search.nav.themeScreen
import com.nugu.ui_core.addFocusCleaner

@Composable
fun SearchRoute(
    parentNavController: NavHostController,
) {
    var searchNavigationState by remember { mutableStateOf<SearchNavigation>(SearchNavigation.Target) }
    val childHostController = rememberNavController()

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
            )
        ) {
            NavHost(
                navController = childHostController,
                startDestination = SearchNavigation.Target.route
            ) {
                targetScreen {
                    TargetChoiceScreen(childHostController)
                    searchNavigationState = SearchNavigation.Target
                }
                themeScreen {
                    ThemeSearchRoute(childHostController)
                    searchNavigationState = SearchNavigation.Theme
                }
                templateScreen {
                    TemplateSearchScreen(childHostController)
                    searchNavigationState = SearchNavigation.Template
                }
            }
        }
    }
}

@Composable
private fun SearchScreen(
    modifier: Modifier = Modifier,
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