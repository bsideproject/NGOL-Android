package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nugu.nuguollim.design_system.theme.Gray200
import com.nugu.nuguollim.design_system.theme.Gray600
import com.nugu.ui_core.singleClick

@Composable
fun NuguMessageToolbar(
    textEditMode: Boolean = false,
    onClickBack: () -> Unit = {},
    onClickClose: () -> Unit = {},
    onClickShareImage: () -> Unit = {},
    onClickSave: () -> Unit = {}
) {
    singleClick { singleClickListener ->
        if (textEditMode) {
            NuguMessageTextToolbar(
                onClickBack = { singleClickListener.onClick(onClickBack) },
                onClickClose = { singleClickListener.onClick(onClickClose) }
            )
        } else {
            NuguMessageEditToolbar(
                onClickBack = { singleClickListener.onClick(onClickBack) },
                onClickShareImage = { singleClickListener.onClick(onClickShareImage) },
                onClickSave = { singleClickListener.onClick(onClickSave) }
            )
        }
    }
}

@Composable
fun NuguMessageTextToolbar(
    onClickBack: () -> Unit = {},
    onClickClose: () -> Unit = {}
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 20.dp),
        ) {
            IconButton(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterStart),
                onClick = { onClickBack() },
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = Color.Black,
                    contentDescription = "back"
                )
            }
            IconButton(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterEnd),
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

@Composable
fun NuguMessageEditToolbar(
    onClickBack: () -> Unit = {},
    onClickShareImage: () -> Unit = {},
    onClickSave: () -> Unit = {}
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 20.dp),
        ) {
            IconButton(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterStart),
                onClick = { onClickBack() },
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = Color.Black,
                    contentDescription = "back"
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
            ) {
                TopButton("공유", onClick = onClickShareImage)
                Spacer(modifier = Modifier.width(6.dp))
                TopButton("저장", onClick = onClickSave)
            }
        }
    }
}

@Composable
private fun TopButton(
    text: String,
    onClick: () -> Unit
) {
    Chip(
        modifier = Modifier,
        onClick = onClick,
        content = {
            PretendardText(
                text = text,
                fontWeight = FontWeight.Normal,
                color = Gray600,
            )
        },
        colors = ChipDefaults.outlinedChipColors(
            backgroundColor = Color.White,
            contentColor = Color.White
        ),
        border = BorderStroke(1.dp, Gray200)
    )
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
        NuguMessageTextToolbar()
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun NuguMessageEditToolbarPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NuguMessageEditToolbar()
    }
}