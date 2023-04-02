package com.nugu.social_login.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nugu.nuguollim.design_system.R
import com.nugu.nuguollim.design_system.theme.*

@Composable
fun KakaoLoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val painter = painterResource(id = R.drawable.ic_login_button_kakao)

    Button(
        modifier = modifier
            .requiredWidth(320.dp)
            .requiredHeight(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = KakaoTextBlack
        ),
        contentPadding = PaddingValues(0.dp),
        onClick = { onClick() },
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
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
            .requiredWidth(320.dp)
            .requiredHeight(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(0.dp),
        onClick = { onClick() },
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
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
    val painter = painterResource(id = R.drawable.ic_icon_google)

    Button(
        modifier = modifier
            .requiredWidth(320.dp)
            .requiredHeight(48.dp)
            .border(width = 1.dp, color = Gray1, shape = RoundedCornerShape(7.dp)),
        shape = RoundedCornerShape(7.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 13.5.dp),
        onClick = { onClick() },
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painter,
                contentDescription = "google logo",
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(50.dp))
            Text(
                text = stringResource(id = R.string.login_google),
                fontFamily = pretendard,
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun LoginHelpButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .border(width = 1.dp, color = Gray2, shape = RoundedCornerShape(50.dp)),
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        onClick = { onClick() },
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        )
    ) {
        Box {
            Text(
                text = stringResource(id = R.string.login_help),
                fontFamily = pretendard,
                color = Gray3,
                fontSize = 12.sp,
                modifier = Modifier
                    .offset(x = 1.dp, y = 1.dp)
                    .alpha(0.3f)
                    .blur(radius = 2.dp)
            )
            Text(
                text = stringResource(id = R.string.login_help),
                fontFamily = pretendard,
                color = Gray3,
                fontSize = 12.sp,
            )
        }
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

@Preview("Google login button", showBackground = true, widthDp = 360, heightDp = 480)
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

@Preview("Login help button", showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun LoginHelpPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginHelpButton {

        }
    }
}