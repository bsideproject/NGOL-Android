package com.nugu.search.nav

import android.net.Uri
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.nugu.nuguollim.common.data.model.search.target.TemplateTargetData
import com.nugu.ui_core.extension.serializableOrNull
import java.io.Serializable

const val NAVIGATION_ROUTE_TARGET = "target"
const val NAVIGATION_ROUTE_THEME = "theme/{data}"
const val NAVIGATION_ROUTE_TEMPLATE = "template/{data}"

sealed class SearchNavigation(val route: String) {
    object Target : SearchNavigation(NAVIGATION_ROUTE_TARGET)
    object Theme : SearchNavigation(NAVIGATION_ROUTE_THEME)
    object Template : SearchNavigation(NAVIGATION_ROUTE_TEMPLATE)
}

class TargetType : NavType<TemplateTargetData>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): TemplateTargetData? =
        bundle.serializableOrNull(key)

    override fun parseValue(value: String): TemplateTargetData =
        Gson().fromJson(value, TemplateTargetData::class.java)

    override fun put(bundle: Bundle, key: String, value: TemplateTargetData) {
        bundle.putSerializable(key, value)
    }
}

class SearchParamsType : NavType<SearchParameterData>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): SearchParameterData? =
        bundle.serializableOrNull(key)

    override fun parseValue(value: String): SearchParameterData =
        Gson().fromJson(value, SearchParameterData::class.java)

    override fun put(bundle: Bundle, key: String, value: SearchParameterData) {
        bundle.putSerializable(key, value)
    }
}

fun NavGraphBuilder.themeScreen(
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = SearchNavigation.Theme.route,
        arguments = listOf(
            navArgument("data") { type = TargetType() }
        ),
        content = content
    )
}

fun NavGraphBuilder.targetScreen(
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = SearchNavigation.Target.route,
        content = content
    )
}

fun NavGraphBuilder.templateScreen(
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = SearchNavigation.Template.route,
        arguments = listOf(
            navArgument("data") { type = SearchParamsType() }
        ),
        content = content
    )
}

fun NavController.navigateToTarget() {
    popBackStack(
        route = SearchNavigation.Target.route,
        inclusive = true
    )
    navigate(SearchNavigation.Target.route) {
        launchSingleTop = true
    }
}

fun NavController.navigateToTheme(targetData: TemplateTargetData) {
    val json = Uri.encode(Gson().toJson(targetData))
    navigate(SearchNavigation.Theme.route.replace("{data}", json)) {
        launchSingleTop = true
        popUpTo(NAVIGATION_ROUTE_TARGET)
    }
}

fun NavController.navigateToTemplate(
    data: SearchParameterData,
    popUpTo: String
) {
    val json = Uri.encode(Gson().toJson(data))

    navigate(SearchNavigation.Template.route.replace("{data}", json.toString())) {
        popUpTo(popUpTo)
    }
}

data class SearchParameterData(
    val targetId: Long? = null,
    val themeId: Long? = null,
    val keyword: String? = null,
) : Serializable