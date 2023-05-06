package com.nugu.nuguollim.ui.message.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.design_system.component.*
import com.nugu.nuguollim.ui.DevicePreviews

@Composable
fun MessageDetailScreen(
    modifier: Modifier = Modifier,
    template: Template,
    onClickTextCopy: (Template) -> Unit = {},
    onClickTextShare: (Template) -> Unit = {},
    onClickTemplateEdit: (Template) -> Unit = {}
) {
    var openDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 20.dp, end = 20.dp, bottom = 26.dp, top = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NuguMessageTitle(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            textTarget = template.target,
            textTheme = template.theme,
        )
        Spacer(modifier = Modifier.height(12.dp))
        NuguSwitch(text1 = "텍스트 추가", text2 = "이미지 편집") {}

        Spacer(modifier = Modifier.height(16.dp))
        NuguMessageText(modifier = Modifier.weight(0.4f), text = template.content)

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            NuguStrokeButton(modifier = Modifier.weight(1f), text = "텍스트 복사") {
                onClickTextCopy(template)
                openDialog = true
            }
            Spacer(modifier = Modifier.width(6.dp))
            NuguStrokeButton(modifier = Modifier.weight(1f), text = "텍스트 공유") {
                onClickTextShare(template)
            }
        }

        Spacer(modifier = Modifier.height(67.dp))
        NuguFillButton(text = "편지지에 쓰기") { onClickTemplateEdit(template) }
    }

    if (openDialog) {
        NuguMessageCopyDialog { openDialog = false }
    }
}

@DevicePreviews
@Composable
private fun MessageDetailScreenPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val template = Template(
            id = 0,
            viewCount = 10,
            target = "교수님",
            theme = "성적문의",
            favorite = false,
            content = "교수님 안녕하세요?\n" +
                    "저는 2023년도 1학기 <누구올림의 이해> 수업을 수강중인 경제학과 정느리라고 합니다.\n" +
                    "다름이 아니라, 어찌구 저찌구 모각모각 어찌구 저찌구 인데 가능할까요?\n" +
                    "감사합니다 감사합니다. 감사합니다. 감사합니다.\n" +
                    "\n" +
                    "하느리\n"
        )

        MessageDetailScreen(template = template)
    }
}