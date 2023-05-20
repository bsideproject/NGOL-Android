package com.nugu.nuguollim.ui.message.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.richeditor.model.RichTextValue
import com.nugu.nuguollim.common.data.model.paper.Paper
import com.nugu.nuguollim.common.data.model.template.Writing
import com.nugu.nuguollim.design_system.component.*
import com.nugu.nuguollim.design_system.theme.NuguollimTheme
import com.nugu.nuguollim.ui.DevicePreviews
import com.nugu.ui_core.addFocusCleaner
import dev.shreyaspatil.capturable.controller.rememberCaptureController

@Composable
fun MessageEditScreen(
    modifier: Modifier = Modifier,
    richTextValue: RichTextValue,
    target: String,
    theme: String,
    papers: List<Paper>,
    onClickTextCopy: (String) -> Unit = {},
    onClickTextShare: (String) -> Unit = {},
    onClose: () -> Unit = {},
    onClickImageShare: (ImageBitmap) -> Unit = {},
    onClickImageSave: (ImageBitmap, Writing) -> Unit = { _, _ -> },
) {
    var openCopyDialog by remember { mutableStateOf(false) }
    var textValue by remember { mutableStateOf(richTextValue) }
    var textEditMode by remember { mutableStateOf(true) }
    var imgColor by remember { mutableStateOf<Color?>(null) }
    var imgBackground by remember { mutableStateOf<String?>(null) }
    val captureController = rememberCaptureController()
    val focusManager = LocalFocusManager.current
    var isClickSaveImage by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .addFocusCleaner(focusManager = focusManager) {}
    ) {
        NuguMessageToolbar(
            textEditMode = textEditMode,
            onClickBack = onClose,
            onClickClose = onClose,
            onClickShareImage = {
                isClickSaveImage = false
                captureController.capture()
            },
            onClickSave = {
                isClickSaveImage = true
                captureController.capture()
            },
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 20.dp, end = 20.dp, bottom = 35.dp, top = 6.dp),
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
            NuguSwitch(text1 = "텍스트 추가", text2 = "이미지 편집") { textEditMode = it }

            Spacer(modifier = Modifier.height(16.dp))
            NuguMessage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                textValue = textValue,
                textEditMode = textEditMode,
                imgColor = imgColor,
                imgBackground = imgBackground,
                captureController = captureController,
                onTextChange = { textValue = it },
                onCaptureBitmap = {
                    if (isClickSaveImage) {
                        val content = textValue.textFieldValue.text
                        val writing = Writing(
                            content = content,
                            paper = imgBackground ?: "",
                            templateId = 0
                        )
                        onClickImageSave(it, writing)
                    } else {
                        onClickImageShare(it)
                    }
                }
            )

            Spacer(modifier = Modifier.weight(1f))
            NuguMessageBottomMenu(
                textEditMode = textEditMode,
                papers = papers,
                onClickTextCopy = {
                    onClickTextCopy(textValue.textFieldValue.text)
                    openCopyDialog = true
                },
                onClickTextShare = { onClickTextShare(textValue.textFieldValue.text) },
                onClickImgColor = {
                    imgColor = it
                    imgBackground = null
                },
                onClickImgBackground = {
                    imgBackground = it
                    imgColor = null
                }
            )
        }
    }

    if (openCopyDialog) {
        NuguMessageCopyDialog { openCopyDialog = false }
    }
}

@Composable
private fun NuguMessageTextMenu(
    onClickTextCopy: () -> Unit = {},
    onClickTextShare: () -> Unit = {},
) {
    Row {
        NuguStrokeButton(modifier = Modifier.weight(1f), text = "텍스트 복사") {
            onClickTextCopy()
        }
        Spacer(modifier = Modifier.width(6.dp))
        NuguStrokeButton(modifier = Modifier.weight(1f), text = "텍스트 공유") {
            onClickTextShare()
        }
    }
}

@Composable
private fun NuguMessageBottomMenu(
    modifier: Modifier = Modifier,
    textEditMode: Boolean,
    papers: List<Paper>,
    onClickTextCopy: () -> Unit = {},
    onClickTextShare: () -> Unit = {},
    onClickImgColor: (Color) -> Unit = {},
    onClickImgBackground: (String) -> Unit = {}
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (textEditMode) {
            NuguMessageTextMenu(
                onClickTextCopy = onClickTextCopy,
                onClickTextShare = onClickTextShare
            )
        } else {
            NuguImageSelectMenu(
                papers = papers,
                onSelectColor = onClickImgColor,
                onSelectBackground = onClickImgBackground
            )
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

            MessageEditScreen(
                richTextValue = textValue,
                target = "교수님",
                theme = "성적문의",
                papers = emptyList()
            )
        }
    }
}