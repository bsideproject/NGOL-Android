package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nugu.nuguollim.design_system.theme.NuguIcons
import com.nugu.nuguollim.design_system.theme.NuguollimTheme

@Composable
fun NuguToolbar(
    navigation: (@Composable () -> Unit)? = null,
    title: (@Composable () -> Unit)? = null
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth(),
    ) {
        val appBarHorizontalPadding = 4.dp
        val titleIconModifier = Modifier
            .fillMaxHeight()
            .width(72.dp - appBarHorizontalPadding)

        Box(modifier = Modifier.height(54.dp)) {
            Row(
                modifier = titleIconModifier,
                verticalAlignment = Alignment.CenterVertically
            ) { navigation?.invoke() }

            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) { title?.invoke() }
        }
    }
}

@Composable
fun NuguToolbarTitle(text: String) {
    ProvideTextStyle(value = MaterialTheme.typography.h6) {
        PretendardText(
            text = text,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )
    }
}

@Preview
@Composable
private fun NuguToolbarPreview() {
    NuguollimTheme {
        NuguToolbar(
            navigation = {
                IconButton(onClick = { }) {
                    NuguIcons.BackArrow
                }
            },
            title = {
                NuguToolbarTitle("제목ㅋㅋ")
            }
        )
    }
}