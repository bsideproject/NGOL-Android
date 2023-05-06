package com.nugu.nuguollim.ui.message.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.richeditor.model.RichTextStyle
import com.mohamedrejeb.richeditor.model.RichTextValue
import com.nugu.nuguollim.design_system.component.*
import com.nugu.nuguollim.design_system.theme.NuguollimTheme
import com.nugu.nuguollim.ui.DevicePreviews

@Composable
fun MessageEditScreen(
    modifier: Modifier = Modifier,
    richTextValue: RichTextValue,
    target: String,
    theme: String,
    onClickTextCopy: (String) -> Unit = {},
    onClickTextShare: (String) -> Unit = {},
    onClickImageShare: () -> Unit = {},
    onClickSave: () -> Unit = {},
    onClose: () -> Unit = {}
) {
    var openCopyDialog by remember { mutableStateOf(false) }
    var openColorDialog by remember { mutableStateOf(false) }
    var textValue by remember { mutableStateOf(richTextValue) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        NuguMessageEditToolbar(
            onClickBack = onClose,
            onClickShareImage = { onClickImageShare() },
            onClickSave = { onClickSave() },
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 20.dp, end = 20.dp, bottom = 26.dp, top = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NuguMessageTitle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                textTarget = target,
                textTheme = theme,
            )
            Spacer(modifier = Modifier.height(12.dp))
            NuguSwitch(text1 = "텍스트 추가", text2 = "이미지 편집") {}

            Spacer(modifier = Modifier.height(16.dp))
            NuguMessageTextField(modifier = Modifier.weight(0.4f), richText = textValue) {
                textValue = it
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row {
                NuguStrokeButton(modifier = Modifier.weight(1f), text = "텍스트 복사") {
                    onClickTextCopy(richTextValue.textFieldValue.text)
                    openCopyDialog = true
                }
                Spacer(modifier = Modifier.width(6.dp))
                NuguStrokeButton(modifier = Modifier.weight(1f), text = "텍스트 공유") {
                    onClickTextShare(richTextValue.textFieldValue.text)
                }
            }
            RichTextStyleRow(
                value = textValue,
                onValueChanged = { textValue = it },
                onClickColor = { openColorDialog = true }
            )
        }
    }
    if (openCopyDialog) {
        NuguMessageCopyDialog { openCopyDialog = false }
    }
    if (openColorDialog) {
        HsvColorPickerDialog{
            val colorStyle = RichTextStyle.TextColor(it)
            textValue = textValue.toggleStyle(colorStyle)
            openColorDialog = false
        }
    }

}

@DevicePreviews
@Composable
private fun MessageEditScreenPreview() {
    NuguollimTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val textValue by remember { mutableStateOf(RichTextValue()) }

            MessageEditScreen(richTextValue = textValue, target = "교수님", theme = "성적문의")
        }
    }
}