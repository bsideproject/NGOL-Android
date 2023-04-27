package com.nugu.nuguollim.ui.login

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.nugu.nuguollim.ui.DevicePreviews
import com.nugu.nuguollim.ui.R
import com.nugu.social_login.login.GoogleLogin
import com.nugu.social_login.login.KakaoLogin
import com.nugu.social_login.login.NaverLogin
import com.nugu.social_login.login.ui.GoogleLoginButton
import com.nugu.social_login.login.ui.KakaoLoginButton
import com.nugu.social_login.login.ui.NaverLoginButton
import com.nugu.nuguollim.common.data.model.auth.AuthInfo
import com.nugu.nuguollim.common.data.model.auth.TokenData
import com.nuguollim.data.state.ResultState
import com.nuguollim.data.usecase.auth.AuthProvide

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    onStartSignUp: (String, String) -> Unit,
    onNavigateToHome: () -> Unit
) {
    val localAuthInfo by viewModel.localAuthInfo.collectAsStateWithLifecycle()
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()

    if (localAuthInfo is ResultState.Success) {
        LaunchedEffect(localAuthInfo) {
            val data = (localAuthInfo as ResultState.Success<AuthInfo>).data
            val type = AuthProvide.Type(data.provideType)
            val id = AuthProvide.Id(data.provideId)

            viewModel.login(type, id)
        }
    }

    when (loginState) {
        is ResultState.Loading -> Unit
        is ResultState.Error -> LaunchedEffect(loginState) {
            onStartSignUp.invoke(viewModel.provideType.data, viewModel.provideId.data)
        }
        is ResultState.Success -> LaunchedEffect(loginState) {
            val data = (loginState as ResultState.Success<TokenData>).data
            viewModel.setToken(AuthProvide.Token(data.token))
            viewModel.setAuthInfo()
            onNavigateToHome.invoke()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        LoginScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            kakaoLogin = viewModel.kakaoLogin,
            naverLogin = viewModel.naverLogin,
            googleLogin = viewModel.googleLogin,
            onLoginSuccess = { type, id -> viewModel.login(type, id) },
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
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            activity = PreviewComponentActivity
        )
    }
}

private object PreviewComponentActivity : ComponentActivity()