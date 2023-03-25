package com.nugu.nuguollim.ui.login

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
import com.nugu.nuguollim.design_system.component.GoogleLoginButton
import com.nugu.nuguollim.design_system.component.KakaoLoginButton
import com.nugu.nuguollim.design_system.component.LoginHelpButton
import com.nugu.nuguollim.design_system.component.NaverLoginButton
import com.nugu.nuguollim.ui.DevicePreviews
import com.nugu.nuguollim.ui.R

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onClickKakaoLogin: () -> Unit = {},
    onClickNaverLogin: () -> Unit = {},
    onClickGoogleLogin: () -> Unit = {}
) {
    val logoPainter = painterResource(id = R.drawable.ic_logo_login)

    Column(
        modifier = modifier
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
        Spacer(modifier = Modifier.height(60.dp))

        LoginHelpButton {}
    }
}

@DevicePreviews
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}