package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

@Composable
fun HsvColorPickerDialog(
    onDismissRequest: (Color) -> Unit = {}
) {
    val controller = rememberColorPickerController()
    var color by remember { mutableStateOf(Color.Black) }
    var hexCode by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismissRequest(color) },
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HsvColorPicker(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .padding(10.dp),
                    controller = controller,
                    onColorChanged = {
                        color = it.color
                        hexCode = it.hexCode
                    }
                )
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = hexCode,
                    color = color
                )
                AlphaTile(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    controller = controller
                )
            }
        },
        confirmButton = {},
        dismissButton = {}
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun HsvColorPickerDialogPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HsvColorPickerDialog()
    }
}