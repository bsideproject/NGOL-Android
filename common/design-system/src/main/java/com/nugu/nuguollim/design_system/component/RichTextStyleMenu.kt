package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedrejeb.richeditor.model.RichTextStyle
import com.mohamedrejeb.richeditor.model.RichTextValue
import com.nugu.nuguollim.design_system.R.drawable
import com.nugu.nuguollim.design_system.theme.Gray500

@Composable
fun RichTextStyleRow(
    modifier: Modifier = Modifier,
    value: RichTextValue,
    onValueChanged: (RichTextValue) -> Unit,
    onTextAlignChanged: (TextAlign) -> Unit
) {
    var openColorDialog by remember { mutableStateOf(false) }
    var openSizeDialog by remember { mutableStateOf(false) }

    val textSizePainter = painterResource(id = drawable.ic_text_size)
    val textColorPainter = painterResource(id = drawable.ic_text_color)

    Row(
        modifier = modifier
    ) {
        RichTextDialogButton(
            modifier = Modifier.size(24.dp),
            painter = textSizePainter
        ) {
            openSizeDialog = true
        }

        RichTextDialogButton(
            modifier = Modifier.size(24.dp),
            painter = textColorPainter
        ) {
            openColorDialog = true
        }

        RichTextAlignButton(
            modifier = Modifier.size(24.dp)
        ) {
            onTextAlignChanged(it)
        }
    }


    if (openSizeDialog) {

        val textSize = value.currentStyles.lastOrNull { it is RichTextStyle.FontSize }?.let {
            (it as RichTextStyle.FontSize).fontSize
        } ?: 10.sp

        ProgressDialog(value = textSize.value.toInt()) {
            onValueChanged(value.toggleStyle(RichTextStyle.FontSize(it.sp)))
            openSizeDialog = false
        }
    }
    if (openColorDialog) {
        HsvColorPickerDialog {
            onValueChanged(value.toggleStyle(RichTextStyle.TextColor(it)))
            openColorDialog = false
        }
    }
}

@Composable
fun RichTextDialogButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    onClick: () -> Unit
) {
    Image(
        modifier = modifier
            .size(24.dp)
            .clickable { onClick() },
        painter = painter,
        contentDescription = "dialog button"
    )
}

@Composable
fun RichTextAlignButton(
    modifier: Modifier = Modifier,
    onClick: (TextAlign) -> Unit
) {
    val alignLeftPainter = painterResource(id = drawable.ic_text_align_left)
    val alignCenterPainter = painterResource(id = drawable.ic_text_align_center)
    val alignRightPainter = painterResource(id = drawable.ic_text_align_right)

    var currentAlignNum by remember { mutableStateOf(0) }
    val currentPainter = when (currentAlignNum) {
        0 -> alignLeftPainter
        1 -> alignCenterPainter
        else -> alignRightPainter
    }

    Image(
        modifier = modifier
            .size(24.dp)
            .clickable {
                currentAlignNum = (currentAlignNum + 1) % 3

                val align = when (currentAlignNum) {
                    0 -> TextAlign.Start
                    1 -> TextAlign.Center
                    else -> TextAlign.End
                }
                onClick(align)
            },
        painter = currentPainter,
        contentDescription = "text align button"
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun RichTextStyleRowPreview() {
    val textValue by remember { mutableStateOf(RichTextValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray500),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RichTextStyleRow(value = textValue, onValueChanged = {}, onTextAlignChanged = {})
    }
}