package com.nugu.nuguollim.ui.login

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nugu.exception.ResponseException
import com.nugu.nuguollim.ui.DevicePreviews
import com.nugu.nuguollim.ui.R
import com.nugu.social_login.login.GoogleLogin
import com.nugu.social_login.login.KakaoLogin
import com.nugu.social_login.login.NaverLogin
import com.nugu.social_login.login.ui.GoogleLoginButton
import com.nugu.social_login.login.ui.KakaoLoginButton
import com.nugu.social_login.login.ui.NaverLoginButton
import com.nuguollim.data.state.ResultState
import com.nuguollim.data.usecase.auth.AuthProvide

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    onStartSignUp: () -> Unit = {}
) {
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()

    when (loginState) {
        is ResultState.Error -> {
            val error = (loginState as ResultState.Error).error

            if (error is ResponseException) {
                if (error.errorCode == ResponseException.ERROR_CODE_USER_ERROR) {
                    onStartSignUp()
                }
            }
        }
        is ResultState.Loading -> {

        }
        is ResultState.Success -> {

        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        LoginScreen(
            modifier = Modifier.fillMaxSize().padding(it),
            kakaoLogin = viewModel.kakaoLogin,
            naverLogin = viewModel.naverLogin,
            googleLogin = viewModel.googleLogin,
            onLoginSuccess = { type, id -> viewModel.saveLocalAuthInfoAndLogin(type, id) },
            onLoginFail = {}
        )
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    activity: ComponentActivity = LocalContext.current as ComponentActivity,
    kakaoLogin: KakaoLogin? = null,
    naverLogin: NaverLogin? = null,
    googleLogin: GoogleLogin? = null,
    onLoginSuccess: (AuthProvide.Type, AuthProvide.Id) -> Unit = { _, _ -> },
    onLoginFail: (String?) -> Unit = {},
) {
    val logoPainter = painterResource(id = R.drawable.ic_logo_login)

    Column(
        modifier = modifier
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .width(160.dp)
                .height(52.43.dp),
            painter = logoPainter,
            contentDescription = "login logo",
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(155.87.dp))

        KakaoLoginButton(
            kakaoLogin = kakaoLogin,
            activity = activity,
            onLoginSuccess = {
                onLoginSuccess(
                    AuthProvide.Type("KAKAO"),
                    AuthProvide.Id(it ?: "")
                )
            },
            onLoginFail = { onLoginFail(it?.message) }
        )
        Spacer(modifier = Modifier.height(10.dp))

        NaverLoginButton(
            naverLogin = naverLogin,
            activity = activity,
            onLoginSuccess = {
                onLoginSuccess(
                    AuthProvide.Type("NAVER"),
                    AuthProvide.Id(it ?: "")
                )
            },
            onLoginFail = { code, message -> onLoginFail(message) }
        )
        Spacer(modifier = Modifier.height(10.dp))

        GoogleLoginButton(
            googleLogin = googleLogin,
            activity = activity,
            onLoginSuccess = {
                onLoginSuccess(
                    AuthProvide.Type("GOOGLE"),
                    AuthProvide.Id(it ?: "")
                )
            },
            onLoginFail = { code, message -> onLoginFail(message) }
        )
    }
}

@DevicePreviews
@Composable
fun PreviewLoginScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        LoginScreen(
            modifier = Modifier.fillMaxSize().padding(it),
            activity = PreviewComponentActivity
        )
    }
}

private object PreviewComponentActivity : ComponentActivity()