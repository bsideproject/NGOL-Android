package com.nugu.nuguollim.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.design_system.component.NuguBottomNavItem
import com.nugu.nuguollim.ui.home.HomeRoute
import com.nugu.nuguollim.ui.search.SearchRoute


/**
 * 메인 화면에 대한 NavHost
 */
@Composable
fun HomeNavHost(
    modifier: Modifier = Modifier,
    onClickTemplate: (Template) -> Unit = {}
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NuguBottomNavItem.Home.screenRoute
    ) {
        composable(NuguBottomNavItem.Home.screenRoute) {
            HomeRoute(navController) { onClickTemplate(it) }
        }
        composable(NuguBottomNavItem.TemplateSearch.screenRoute) {
            SearchRoute(navController) { onClickTemplate(it) }
        }
        composable(NuguBottomNavItem.Community.screenRoute) {}
    }
}