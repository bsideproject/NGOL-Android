package com.nugu.nuguollim.ui

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nugu.nuguollim.ui.login.LoginScreen
import com.nugu.nuguollim.ui.login.LoginViewModel

@Composable
fun NuGuOlLimApp(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = viewModel(),
    activity: ComponentActivity
) {
    val navController = rememberNavController()
    val naverLauncher: ActivityResultLauncher<Intent> = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        when (result.resultCode) {
            Activity.RESULT_OK -> {

            }
            Activity.RESULT_CANCELED -> {

            }
        }
    }
    val googleLauncher: ActivityResultLauncher<Intent> = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        when (result.resultCode) {
            Activity.RESULT_OK -> {

            }
            Activity.RESULT_CANCELED -> {

            }
        }
    }

    Scaffold(
        modifier = modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = NuGuScreen.SignIn.name,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(route = NuGuScreen.SignIn.name) {
                LoginScreen(
                    onClickKakaoLogin = {
                        loginViewModel.kakaoLogin.login(activity) { token, error ->
                        }
                    },
                    onClickNaverLogin = {
                        loginViewModel.naverLogin.login(activity, naverLauncher)
                    },
                    onClickGoogleLogin = {
                        loginViewModel.googleLogin.login(activity, googleLauncher)
                    }
                )
            }
        }
    }
}