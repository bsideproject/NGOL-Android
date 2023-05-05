package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NuguMessageToolbar(
    onClickBack: () -> Unit = {},
    onClickClose: () -> Unit = {}
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 20.dp),
        ) {
            IconButton(
                modifier = Modifier.size(24.dp).align(Alignment.CenterStart),
                onClick = { onClickBack() },
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = Color.Black,
                    contentDescription = "back"
                )
            }
            IconButton(
                modifier = Modifier.size(24.dp).align(Alignment.CenterEnd),
                onClick = { onClickClose() },
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    tint = Color.Black,
                    contentDescription = "close"
                )
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun NuguMessageToolbarPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NuguMessageToolbar()
    }
}