package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nugu.nuguollim.design_system.theme.Black
import com.nugu.nuguollim.design_system.theme.Gray100
import com.nugu.nuguollim.design_system.theme.Gray500
import com.nugu.nuguollim.design_system.theme.pretendard


@Composable
fun NuguMessageText(
    modifier: Modifier = Modifier,
    text: String,
) {
    val currentTextLength = text.length
    val maxTextLength = 400

    Box(
        modifier = modifier.background(
            color = Gray100,
            shape = RoundedCornerShape(10.dp)
        )
    ) {
        Text(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            text = text,
            fontFamily = pretendard,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Black
        )
        Text(
            modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 20.dp, end = 16.dp),
            text = "${currentTextLength}/${maxTextLength}",
            fontFamily = pretendard,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Gray500
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun NuguMessageTextPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray500),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val text = "교수님 안녕하세요?\n" +
                "저는 2023년도 1학기 <누구올림의 이해> 수업을 수강중인 경제학과 정느리라고 합니다.\n" +
                "다름이 아니라, 어찌구 저찌구 모각모각 어찌구 저찌구 인데 가능할까요?\n" +
                "감사합니다 감사합니다. 감사합니다. 감사합니다.\n" +
                "\n" +
                "하느리\n"
        NuguMessageText(text = text)
    }
}