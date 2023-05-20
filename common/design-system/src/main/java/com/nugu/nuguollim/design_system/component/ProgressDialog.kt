package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProgressDialog(
    value: Int,
    min: Int = 8,
    max: Int = 40,
    onDismissRequest: (Int) -> Unit = {}
) {
    var currentValue by remember { mutableStateOf(value) }

    AlertDialog(
        onDismissRequest = { onDismissRequest(currentValue) },
        title = {},
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = currentValue.toString())
                Slider(
                    modifier = Modifier.fillMaxWidth(),
                    value = currentValue.toFloat(),
                    onValueChange = { currentValue = it.toInt() },
                    valueRange = min.toFloat()..max.toFloat(),
                    steps = max - min,
                    colors = SliderDefaults.colors(
                        activeTickColor = Color.Transparent,
                        inactiveTickColor = Color.Transparent
                    )
                )
            }
        },
        confirmButton = {},
        dismissButton = {}
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun ProgressDialogPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProgressDialog(10)
    }
}