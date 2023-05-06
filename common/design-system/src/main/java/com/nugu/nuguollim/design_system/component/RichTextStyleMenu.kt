package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import com.mohamedrejeb.richeditor.model.RichTextStyle
import com.mohamedrejeb.richeditor.model.RichTextValue


@Composable
fun RichTextStyleRow(
    modifier: Modifier = Modifier,
    value: RichTextValue,
    onValueChanged: (RichTextValue) -> Unit,
    onClickColor: () -> Unit = {}
) {
    Row(
        modifier = modifier
    ) {
        RichTextStyleButton(
            style = RichTextStyle.Bold,
            value = value,
            onValueChanged = onValueChanged,
            icon = Icons.Outlined.FormatBold
        )

        RichTextStyleButton(
            style = RichTextStyle.Italic,
            value = value,
            onValueChanged = onValueChanged,
            icon = Icons.Outlined.FormatItalic
        )

        RichTextStyleButton(
            style = RichTextStyle.Underline,
            value = value,
            onValueChanged = onValueChanged,
            icon = Icons.Outlined.FormatUnderlined
        )

        RichTextStyleButton(
            style = RichTextStyle.Strikethrough,
            value = value,
            onValueChanged = onValueChanged,
            icon = Icons.Outlined.FormatStrikethrough
        )

        RichTextStyleButton(
            style = RichTextStyle.FontSize(28.sp),
            value = value,
            onValueChanged = onValueChanged,
            icon = Icons.Outlined.FormatSize
        )

        RichTextStyleButton(
            style = RichTextStyle.TextColor(Color.Black),
            value = value,
            onValueChanged = { onClickColor() },
            icon = Icons.Filled.Circle,
            tint = value.currentStyles.lastOrNull { it is RichTextStyle.TextColor }?.let {
                (it as RichTextStyle.TextColor).color
            }
        )
    }
}

@Composable
fun RichTextStyleButton(
    style: RichTextStyle,
    value: RichTextValue,
    onValueChanged: (RichTextValue) -> Unit,
    icon: ImageVector,
    tint: Color? = null
) {
    androidx.compose.material3.IconButton(
        modifier = Modifier
            // Workaround to prevent the rich editor
            // from losing focus when clicking on the button
            // (Happens only on Desktop)
            .focusProperties { canFocus = false },
        onClick = {
            onValueChanged(value.toggleStyle(style))
        },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = if (value.currentStyles.contains(style)) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.onBackground
            },
        ),
    ) {
        Icon(
            icon,
            contentDescription = icon.name,
            tint = tint ?: LocalContentColor.current,
            modifier = Modifier
                .background(
                    color = if (value.currentStyles.contains(style)) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        Color.Transparent
                    },
                    shape = CircleShape
                )
        )
    }
}
