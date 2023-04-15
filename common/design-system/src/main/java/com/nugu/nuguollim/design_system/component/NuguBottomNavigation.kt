package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nugu.nuguollim.design_system.R
import com.nugu.nuguollim.design_system.theme.Gray400
import com.nugu.nuguollim.design_system.theme.Primary500
import com.nugu.nuguollim.design_system.theme.pretendard

sealed class NuguBottomNavItem(
    val title: Int, val icon: Int, val iconSelected: Int, val screenRoute: String
) {
    object Home : NuguBottomNavItem(
        R.string.title_home,
        R.drawable.ic_home,
        R.drawable.ic_home_selected,
        "home"
    )

    object TemplateSearch : NuguBottomNavItem(
        R.string.title_template_search,
        R.drawable.ic_template_search,
        R.drawable.ic_template_search_selected,
        "templateSearch"
    )

    object Community :
        NuguBottomNavItem(
            R.string.title_community,
            R.drawable.ic_community,
            R.drawable.ic_community_selected,
            "community"
        )
}

@Composable
fun NuguBottomNavigation(
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        NuguBottomNavItem.Home,
        NuguBottomNavItem.TemplateSearch,
        NuguBottomNavItem.Community
    )

    Card(
        elevation = 8.dp,
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            items.forEach { item ->
                val isSelected = currentRoute == item.screenRoute
                val iconId = if (isSelected) item.iconSelected else item.icon
                val labelColor = if (isSelected) Primary500 else Gray400

                Column(
                    modifier = Modifier
                        .width(66.dp)
                        .height(42.dp)
                        .clickable {
                            navController.navigate(item.screenRoute) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = iconId),
                        contentDescription = stringResource(id = item.title),
                        modifier = Modifier.size(20.dp),
                        tint = labelColor
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(id = item.title),
                        fontFamily = pretendard,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = labelColor
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun NuguBottomNavigationPreview() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NuguBottomNavigation(navController)
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize().background(Color.White).padding(it)
        )
    }
}