package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nugu.nuguollim.design_system.theme.Gray200
import com.nugu.nuguollim.design_system.theme.Primary500
import com.nugu.nuguollim.design_system.theme.pretendard

@Composable
fun NuguFillButton(
    modifier: Modifier = Modifier,
    text: String = "",
    active: Boolean = true,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(5.dp),
                color = if (active) Color(0xFFFF7043) else Color.Gray
            )
            .clickable { if (active) onClick() }
            .padding(horizontal = 10.dp, vertical = 14.5.dp),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = text,
            fontFamily = pretendard,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun NuguFillButton(
    modifier: Modifier = Modifier,
    text: String = "",
    active: Boolean = true,
    activeButtonColor: Color = Primary500,
    inactiveButtonColor: Color = Gray200,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.White,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(5.dp),
                color = if (active) activeButtonColor else inactiveButtonColor
            )
            .clickable { if (active) onClick() }
            .padding(horizontal = 10.dp, vertical = 14.5.dp),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = text,
            fontFamily = pretendard,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = if (active) activeTextColor else inactiveTextColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun NuguStrokeButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(5.dp),
                color = Color.White
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(5.dp),
                color = Primary500
            )
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 10.dp),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            fontFamily = pretendard,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Primary500,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun NuguFillButtonPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NuguFillButton(text = "가입 완료하기")
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun NuguStrokeButtonPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NuguStrokeButton(text = "텍스트 복사")
    }
}