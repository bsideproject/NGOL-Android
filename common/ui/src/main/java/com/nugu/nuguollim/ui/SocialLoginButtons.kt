package com.nugu.nuguollim.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nugu.nuguollim.design_system.theme.KakaoTextBlack
import com.nugu.nuguollim.design_system.theme.KakaoYellow
import com.nugu.nuguollim.design_system.theme.NaverGreen

@Composable
fun KakaoLoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val painter = painterResource(id = R.drawable.ic_login_button_kakao)

    Button(
        modifier = modifier
            .requiredWidth(107.dp)
            .requiredHeight(38.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = KakaoYellow,
            contentColor = KakaoTextBlack
        ),
        contentPadding = PaddingValues(0.dp),
        onClick = { onClick() },
        elevation = ButtonDefaults.elevation(
            defaultElevation = 6.dp,
            pressedElevation = 8.dp,
            disabledElevation = 0.dp
        )
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentDescription = "kakao login",
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun NaverLoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val painter = painterResource(id = R.drawable.ic_login_button_naver)

    Button(
        modifier = modifier
            .requiredWidth(181.dp)
            .requiredHeight(34.dp),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = NaverGreen,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(0.dp),
        onClick = { onClick() },
        elevation = ButtonDefaults.elevation(
            defaultElevation = 6.dp,
            pressedElevation = 8.dp,
            disabledElevation = 0.dp
        )
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentDescription = "naver login",
            contentScale = ContentScale.Fit,
        )
    }
}

@Composable
fun GoogleLoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val painter = painterResource(id = R.drawable.ic_login_button_google)

    Button(
        modifier = modifier
            .requiredWidth(173.dp)
            .requiredHeight(34.dp),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(0.dp),
        onClick = { onClick() },
        elevation = ButtonDefaults.elevation(
            defaultElevation = 6.dp,
            pressedElevation = 8.dp,
            disabledElevation = 0.dp
        )
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentDescription = "google login",
            contentScale = ContentScale.Fit,
        )
    }
}

@Preview("Kakao login button", showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun KakaoLoginPreview() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        KakaoLoginButton {

        }
    }
}

@Preview("Naver login button", showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun NaverLoginPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NaverLoginButton {

        }
    }
}

@Preview("Google login button", showBackground = true , widthDp = 360, heightDp = 480)
@Composable
private fun GoogleLoginPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GoogleLoginButton {

        }
    }
}