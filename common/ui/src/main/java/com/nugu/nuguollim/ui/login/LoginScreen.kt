package com.nugu.nuguollim.ui.login

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nugu.nuguollim.ui.DevicePreviews
import com.nugu.nuguollim.ui.R
import com.nugu.social_login.login.ui.GoogleLoginButton
import com.nugu.social_login.login.ui.KakaoLoginButton
import com.nugu.social_login.login.ui.NaverLoginButton

@Composable
fun LoginRoute(
    activity: ComponentActivity? = null,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val naverLauncher: ActivityResultLauncher<Intent> = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        when (result.resultCode) {
            Activity.RESULT_OK -> {}
            Activity.RESULT_CANCELED -> {}
        }
    }
    val googleLauncher: ActivityResultLauncher<Intent> = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        when (result.resultCode) {
            Activity.RESULT_OK -> {}
            Activity.RESULT_CANCELED -> {}
        }
    }

    LoginScreen(
        onClickKakaoLogin = {
            if (activity != null) {
                viewModel.kakaoLogin.login(activity) { token, error -> }
            }
        },
        onClickNaverLogin = {
            if (activity != null) {
                viewModel.naverLogin.login(activity, naverLauncher)
            }
        },
        onClickGoogleLogin = {
            if (activity != null) {
                viewModel.googleLogin.login(activity, googleLauncher)
            }
        }
    )
}

@Composable
fun LoginScreen(
    onClickKakaoLogin: () -> Unit = {},
    onClickNaverLogin: () -> Unit = {},
    onClickGoogleLogin: () -> Unit = {},
) {
    val logoPainter = painterResource(id = R.drawable.ic_logo_login)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .width(160.dp)
                .height(52.43.dp),
            painter = logoPainter,
            contentDescription = "login login",
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(155.87.dp))

        KakaoLoginButton { onClickKakaoLogin() }
        Spacer(modifier = Modifier.height(10.dp))

        NaverLoginButton { onClickNaverLogin() }
        Spacer(modifier = Modifier.height(10.dp))

        GoogleLoginButton { onClickGoogleLogin() }
    }
}

@DevicePreviews
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}