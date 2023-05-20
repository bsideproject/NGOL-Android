package com.nugu.nuguollim.navigation

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nugu.nuguollim.LoginInActivity
import com.nugu.nuguollim.app.R
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.design_system.component.NuguBottomNavItem
import com.nugu.nuguollim.ui.home.HomeRoute
import com.nugu.nuguollim.ui.my_page.MyPageRoute
import com.nugu.nuguollim.ui.search.SearchRoute
import com.nugu.system_info.DeviceInfo


/**
 * 메인 화면에 대한 NavHost
 */
@Composable
fun HomeNavHost(
    modifier: Modifier = Modifier,
    onClickTemplate: (Template) -> Unit = {}
) {
    val activity = LocalContext.current as Activity
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
        composable(NuguBottomNavItem.MyPage.screenRoute) {
            MyPageRoute(
                navController = navController,
                onMoveLoginPage = {
                    val intent = Intent(activity, LoginInActivity::class.java)
                    activity.startActivity(intent)
                    activity.finish()
                },
                onSendMail = { nickname, subject ->
                    activity.sendMail(nickname, subject)
                },
                onClickTemplate = onClickTemplate
            )
        }
    }
}

private fun Activity.sendMail(nickname: String, subject: String) {
    val text = "닉네임: $nickname\n앱 버전: ${DeviceInfo.appVersion}\nOS: Android SDK ${DeviceInfo.deviceSdkVersion}\n디바이스: ${DeviceInfo.deviceModel}\n내용: "
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "plain/text"
        putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.nugu_email)))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, text)
    }
    startActivity(intent)
}