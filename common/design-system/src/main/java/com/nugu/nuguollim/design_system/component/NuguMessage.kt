package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.richeditor.model.RichTextValue
import com.nugu.nuguollim.design_system.theme.Gray100

@Composable
fun NuguMessage(
    modifier: Modifier = Modifier,
    textValue: RichTextValue,
    textEditMode: Boolean,
    onTextChange: (RichTextValue) -> Unit = {},
    imgColor: Color? = null,
    imgBackground: String? = null
) {
    Box(
        modifier = modifier.background(
            color = Gray100,
            shape = RoundedCornerShape(10.dp)
        )
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
            showTextLimit = textEditMode,
            onTextChange = onTextChange
        )
    }
}