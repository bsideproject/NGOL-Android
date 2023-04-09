package com.nugu.nuguollim.ui.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nugu.nuguollim.design_system.component.NuguFillButton
import com.nugu.nuguollim.design_system.theme.pretendard
import com.nugu.nuguollim.ui.DevicePreviews

@Composable
fun WelcomeScreen(
    onClickNext: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(start = 21.dp)
        ) {
            Text(
                text = "가입 완료!",
                color = Color(0xFF151516),
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 26.sp
            )
            Spacer(modifier = Modifier.height(82.dp))
            Text(
                text = "이제 누구올림과 함께\n" +
                        "다양한 상황에서의 문구 템플릿으로\n" +
                        "손에 땀 흘리지 마세요!",
                color = Color(0xFF151516),
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )
        }


        NuguFillButton(
            text = "문구 템플릿 찾으러 가기",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 18.dp, start = 20.dp, end = 20.dp),
            onClick = { onClickNext() })
    }
}

@DevicePreviews
@Composable
private fun WelcomeScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeScreen()
    }
}