package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nugu.nuguollim.design_system.theme.Gray200
import com.nugu.nuguollim.design_system.theme.Gray800
import com.nugu.nuguollim.design_system.theme.NuguollimTheme
import com.nugu.nuguollim.design_system.theme.Primary100
import com.nugu.nuguollim.design_system.theme.Primary500

@Composable
fun NuguChip(
    text: String,
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val fontWeight = if (isChecked) FontWeight.SemiBold else FontWeight.Normal
    val backgroundColor = if (isChecked) Primary100 else Color.White
    val contentColor = if (isChecked) Primary500 else Gray800
    val border = if (isChecked) BorderStroke(1.dp, Primary500) else BorderStroke(1.dp, Gray200)
    val textColor = if (isChecked) Primary500 else Gray800

    Chip(
        modifier = modifier,
        onClick = onClick,
        content = {
            PretendardText(
                text = text,
                fontWeight = fontWeight,
                color = textColor,
            )
        },
        colors = ChipDefaults.outlinedChipColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        border = border
    )
}

@Preview
@Composable
private fun NuguChipPreview() {
    NuguollimTheme {
        NuguChip("누구 올림", true) {}
    }
}