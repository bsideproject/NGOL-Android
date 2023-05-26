package com.nugu.my_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.google.gson.Gson
import com.mohamedrejeb.richeditor.model.RichTextPart
import com.mohamedrejeb.richeditor.model.RichTextStyle
import com.mohamedrejeb.richeditor.model.RichTextValue
import com.nugu.nuguollim.common.data.model.template.MyWritingTemplateData
import com.nugu.nuguollim.design_system.component.NuguFillButton
import com.nugu.nuguollim.design_system.component.NuguMessage
import com.nugu.nuguollim.design_system.component.NuguMessageTitle
import com.nugu.nuguollim.design_system.component.NuguMessageToolbar
import com.nugu.nuguollim.design_system.theme.Gray200
import com.nugu.nuguollim.design_system.theme.Gray700
import com.nugu.nuguollim.design_system.ui_tools.dimClear
import dev.shreyaspatil.capturable.controller.rememberCaptureController

@Composable
fun MyMessageDetailDialog(
    myWritingTemplateData: MyWritingTemplateData,
    onMovePageMessageEdit: (Long) -> Unit = {},
    onDeleteTemplate: (Long) -> Unit = {},
    onDismissRequest: () -> Unit = {},
    onClickImageSave: (ImageBitmap) -> Unit,
    onClickImageShare: (ImageBitmap) -> Unit,
) {
    val captureController = rememberCaptureController()
    var showDeleteTemplateConfirmDialog by remember { mutableStateOf(Pair(-1L, false)) }
    var isClickSaveImage by remember { mutableStateOf(false) }

    if (showDeleteTemplateConfirmDialog.second) {
        DeleteTemplateConfirmDialog(
            id = showDeleteTemplateConfirmDialog.first,
            onDeleteTemplate = onDeleteTemplate,
            onDismissRequest = { showDeleteTemplateConfirmDialog = Pair(-1L, false) }
        )
    }

    Dialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        onDismissRequest = onDismissRequest,
    ) {
        LocalView.current.dimClear()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            val richTextValue = try {
                Gson().fromJson(myWritingTemplateData.content, RichTextValue::class.java)
            } catch (e: Exception) {
                RichTextValue(
                    text = myWritingTemplateData.content,
                    parts = listOf(
                        RichTextPart(
                            fromIndex = 0,
                            toIndex = myWritingTemplateData.content.length,
                            styles = setOf(
                                RichTextStyle.TextColor(Color.Black),
                            )
                        )
                    )
                )
            }
            NuguMessageToolbar(
                textEditMode = false,
                onClickBack = onDismissRequest,
                onClickClose = onDismissRequest,
                onClickShareImage = {
                    isClickSaveImage = false
                    captureController.capture()
                },
                onClickSave = {
                    isClickSaveImage = true
                    captureController.capture()
                }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 20.dp, end = 20.dp, bottom = 35.dp, top = 6.dp)
            ) {
                NuguMessageTitle(
                    textTarget = myWritingTemplateData.target,
                    textTheme = myWritingTemplateData.theme,
                    subTextTarget = "에게 올렸던",
                    subTextTheme = " 메시지"
                )
                Spacer(modifier = Modifier.height(28.dp))
                NuguMessage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    textValue = richTextValue,
                    textEditMode = false,
                    imgColor = null,
                    imgBackground = myWritingTemplateData.paper,
                    captureController = captureController,
                    onCaptureBitmap = {
                        if (isClickSaveImage) {
                            onClickImageSave(it)
                        } else {
                            onClickImageShare(it)
                        }
                    }
                )
                Row(
                    modifier = Modifier
                        .padding(top = 35.dp)
                        .fillMaxWidth()
                ) {
                    NuguFillButton(
                        modifier = Modifier.weight(1f),
                        activeButtonColor = Gray200,
                        activeTextColor = Gray700,
                        text = "삭제하기",
                        onClick = { showDeleteTemplateConfirmDialog = Pair(myWritingTemplateData.id, true) }
                    )
                    Spacer(modifier = Modifier.padding(start = 6.dp))
                    NuguFillButton(
                        modifier = Modifier.weight(1f),
                        text = "템플릿 보러가기",
                        onClick = { onMovePageMessageEdit.invoke(myWritingTemplateData.templateId) }
                    )
                }
            }
        }
    }
}