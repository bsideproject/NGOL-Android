package com.nugu.nuguollim.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nugu.nuguollim.ui.sign_up.SignUpRoute
import com.nugu.nuguollim.ui.welcome.WelcomeScreen

/**
 * 회원 가입 화면에 대한 NavHost
 */
@Composable
fun SignUpNavHost(onClickNextActivity: () -> Unit) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "signUp") {
        composable("signUp") { SignUpRoute(navController) }
        composable("welcome") { WelcomeScreen(onClickNext = onClickNextActivity) }
    }
}
