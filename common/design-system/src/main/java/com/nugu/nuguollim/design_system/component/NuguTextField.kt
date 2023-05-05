package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.nugu.nuguollim.design_system.theme.Black
import com.nugu.nuguollim.design_system.theme.pretendard

@Composable
fun NuguTextField(
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    textStyle: TextStyle = TextStyle(
        color = Black,
        fontSize = 16.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal
    ),
    placeHolderStyle: PlaceHolderStyle,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        maxLines = maxLines,
        textStyle = textStyle,
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
    )

    if (value.isEmpty()) {
        PretendardText(
            text = placeHolderStyle.placeHolder,
            fontSize = placeHolderStyle.fontSize,
            color = placeHolderStyle.color,
            modifier = placeHolderStyle.modifier
        )
    }
}

@Immutable
data class PlaceHolderStyle(
    val placeHolder: String = "",
    val fontSize: TextUnit = TextUnit.Unspecified,
    val color: Color = Color.Unspecified,
    val modifier: Modifier = Modifier
)