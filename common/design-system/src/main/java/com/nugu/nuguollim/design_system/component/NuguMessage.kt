package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedrejeb.richeditor.model.RichTextValue
import com.nugu.nuguollim.design_system.theme.Gray100
import com.nugu.nuguollim.design_system.theme.Gray500
import com.nugu.nuguollim.design_system.theme.pretendard
import dev.shreyaspatil.capturable.Capturable
import dev.shreyaspatil.capturable.controller.CaptureController

@Composable
fun NuguMessage(
    modifier: Modifier = Modifier,
    textValue: RichTextValue,
    textEditMode: Boolean,
    imgColor: Color? = null,
    imgBackground: String? = null,
    captureController: CaptureController,
    onTextChange: (RichTextValue) -> Unit = {},
    onCaptureBitmap: (ImageBitmap) -> Unit = {}
) {
    val maxTextLength = 400

    Box(
        modifier = modifier.background(
            color = Gray100,
            shape = RoundedCornerShape(10.dp)
        )
    ) {
        NuguMessageContent(
            modifier = Modifier.fillMaxSize(),
            captureController = captureController,
            textEditMode = textEditMode,
            textValue = textValue,
            imgColor = imgColor,
            imgBackground = imgBackground,
            onTextChange = onTextChange,
            onCaptureSuccess = onCaptureBitmap,
            onCaptureFail = {}
        )

        if (textEditMode) {
            NuguMessageInfo(
                modifier = Modifier.fillMaxSize(),
                messageRichText = textValue,
                maxTextLength = maxTextLength
            )
        }
    }
}

@Composable
fun NuguMessageContent(
    modifier: Modifier = Modifier,
    captureController: CaptureController,
    textEditMode: Boolean,
    textValue: RichTextValue,
    imgColor: Color? = null,
    imgBackground: String? = null,
    onTextChange: (RichTextValue) -> Unit = {},
    onCaptureSuccess: (ImageBitmap) -> Unit = {},
    onCaptureFail: (Throwable) -> Unit = {}
) {
    Capturable(
        modifier = modifier
            .background(Color.Transparent, shape = RoundedCornerShape(10.dp))
            .clip(shape = RoundedCornerShape(10.dp)),
        controller = captureController,
        onCaptured = { bitmap, error ->
            if (bitmap != null) {
                onCaptureSuccess(bitmap)
            }
            if (error != null) {
                onCaptureFail(error)
            }
        }
    ) {
        if (textEditMode.not()) {
            NuguMessageImage(
                modifier = Modifier.fillMaxSize(),
                color = imgColor,
                imageUrl = imgBackground
            )
        }
        NuguMessageTextField(
            modifier = Modifier.fillMaxSize(),
            richText = textValue,
            enable = textEditMode,
            onTextChange = onTextChange
        )
    }
}

@Composable
fun NuguMessageTool(

) {

}

@Composable
fun NuguMessageInfo(
    modifier: Modifier = Modifier,
    messageRichText: RichTextValue,
    maxTextLength: Int = 400
) {
    val currentTextLength = messageRichText.textFieldValue.text.length

    Box(
        modifier = modifier
    ) {
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