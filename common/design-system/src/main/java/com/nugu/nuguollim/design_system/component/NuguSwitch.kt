package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nugu.nuguollim.design_system.theme.Gray100
import com.nugu.nuguollim.design_system.theme.Gray500
import com.nugu.nuguollim.design_system.theme.Primary500
import com.nugu.nuguollim.design_system.theme.pretendard

@Composable
fun NuguSwitch(
    modifier: Modifier = Modifier,
    text1: String,
    text2: String,
    onChecked: (Boolean) -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    var checked by remember { mutableStateOf(true) }

    Card(
        shape = RoundedCornerShape(500.dp),
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                checked = checked.not()
                onChecked(checked)
            },
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier.background(Gray100),
            horizontalArrangement = Arrangement.Center
        ) {
            NuguSwitchThumb(
                checked = checked,
                text = text1
            )
            NuguSwitchThumb(
                checked = checked.not(),
                text = text2
            )
        }
    }
}

@Composable
fun NuguSwitchThumb(
    modifier: Modifier = Modifier,
    checked: Boolean,
    text: String
) {
    Card(
        shape = RoundedCornerShape(500.dp),
        modifier = modifier
            .width(95.dp)
            .height(34.dp),
        elevation = 0.dp,
    ) {
        val backgroundColor = if (checked) Primary500 else Gray100
        val textColor = if (checked) Color.White else Gray500

        Box(
            modifier = Modifier.background(backgroundColor),
        ) {
            Text(
                text = text,
                modifier = Modifier.align(Alignment.Center),
                color = textColor,
                fontFamily = pretendard,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun NuguSwitchPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray500),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NuguSwitch(text1 = "텍스트 추가", text2 = "이미지 편집")
    }
}


@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun NuguSwitchThumbPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray500),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NuguSwitchThumb(text = "텍스트 추가", checked = false)
    }
}