package com.nugu.social_login.login.ui

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.navercorp.nid.NaverIdLoginSDK
import com.nugu.nuguollim.design_system.R
import com.nugu.nuguollim.design_system.theme.*
import com.nugu.social_login.login.GoogleLogin
import com.nugu.social_login.login.KakaoLogin
import com.nugu.social_login.login.NaverLogin

@Composable
fun KakaoLoginButton(
    modifier: Modifier = Modifier,
    activity: ComponentActivity = LocalContext.current as ComponentActivity,
    kakaoLogin: KakaoLogin? = null,
    onLoginSuccess: (String?) -> Unit = {},
    onLoginFail: (Throwable?) -> Unit = {},
) {
    val painter = painterResource(id = R.drawable.ic_login_button_kakao)
    val kakaoLoginResult = { token: String?, error: Throwable? ->
        if (error != null) {
            onLoginFail(error)
        }
        if (token != null) {
            kakaoLogin?.getId { token ->
                kakaoLogin.logout { }
                onLoginSuccess(token)
            }
        }
    }

    Button(
        modifier = modifier
            .requiredWidth(320.dp)
            .requiredHeight(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = KakaoTextBlack
        ),
        contentPadding = PaddingValues(0.dp),
        onClick = { kakaoLogin?.login(activity, kakaoLoginResult) },
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
    activity: ComponentActivity = LocalContext.current as ComponentActivity,
    naverLogin: NaverLogin? = null,
    onLoginSuccess: (String?) -> Unit = {},
    onLoginFail: (String, String?) -> Unit = { code, message -> },
) {
    val painter = painterResource(id = R.drawable.ic_login_button_naver)
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    naverLogin?.getId { token ->
                        naverLogin.logout()
                        onLoginSuccess(token)
                    }
                }
                Activity.RESULT_CANCELED -> {
                    val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                    val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                    onLoginFail(errorCode, errorDescription)
                }
            }
        }
    )

    Button(
        modifier = modifier
            .requiredWidth(320.dp)
            .requiredHeight(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(0.dp),
        onClick = { naverLogin?.login(activity, launcher) },
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
    activity: ComponentActivity = LocalContext.current as ComponentActivity,
    googleLogin: GoogleLogin? = null,
    onLoginSuccess: (String?) -> Unit = {},
    onLoginFail: (Int, String?) -> Unit = { code, message -> },
) {
    val painter = painterResource(id = R.drawable.ic_google)
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            val intent = result.data
            val completedTask: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(intent)

            try {
                completedTask.getResult(ApiException::class.java)?.also {
                    googleLogin?.getId(activity) { token ->
                        googleLogin.logout()
                        onLoginSuccess(token)
                    }
                }
            } catch (e: ApiException) {
                onLoginFail(e.statusCode, e.message)
            }
        }
    )

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
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp),
        onClick = { googleLogin?.login(activity, launcher) },
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                modifier = Modifier.size(24.dp)
                    .align(Alignment.CenterStart),
                painter = painter,
                contentDescription = "google logo",
                contentScale = ContentScale.Fit
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = stringResource(id = R.string.login_google),
                fontFamily = pretendard,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}

@Composable
fun LoginHelpButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
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
        KakaoLoginButton(activity = PreviewComponentActivity)
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
        NaverLoginButton(activity = PreviewComponentActivity)
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
        GoogleLoginButton(activity = PreviewComponentActivity)
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
        LoginHelpButton()
    }
}

private object PreviewComponentActivity : ComponentActivity()