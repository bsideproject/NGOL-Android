package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedrejeb.richeditor.model.RichTextValue
import com.mohamedrejeb.richeditor.ui.material.RichTextEditor
import com.nugu.nuguollim.design_system.theme.Gray500
import com.nugu.nuguollim.design_system.theme.pretendard


@Composable
fun NuguMessageTextField(
    modifier: Modifier = Modifier,
    richText: RichTextValue,
    enable: Boolean = true,
    showTextLimit: Boolean = true,
    onTextChange: (RichTextValue) -> Unit = {},
) {
    val currentTextLength = richText.textFieldValue.text.length
    val maxTextLength = 400

    Box(
        modifier = modifier
    ) {
        RichTextEditor(
            modifier = Modifier
                .fillMaxSize(),
            value = richText,
            onValueChange = {
                if (currentTextLength <= maxTextLength) {
                    onTextChange(it)
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            enabled = enable,
        )
        if (showTextLimit) {
            Text(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 20.dp, end = 16.dp),
                text = "${currentTextLength}/${maxTextLength}",
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Gray500
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun NuguMessageTextFieldPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray500),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by remember {
            mutableStateOf(
                RichTextValue(text =
                "교수님 안녕하세요?\n" +
                        "저는 2023년도 1학기 <누구올림의 이해> 수업을 수강중인 경제학과 정느리라고 합니다.\n" +
                        "다름이 아니라, 어찌구 저찌구 모각모각 어찌구 저찌구 인데 가능할까요?\n" +
                        "감사합니다 감사합니다. 감사합니다. 감사합니다.\n" +
                        "\n" +
                        "하느리\n"
                )
            )
        }
        NuguMessageTextField(richText = text) { text = it }
    }
}