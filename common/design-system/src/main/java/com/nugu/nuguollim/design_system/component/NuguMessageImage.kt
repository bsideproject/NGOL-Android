package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun NuguMessageImage(
    modifier: Modifier = Modifier,
    color: Color? = null,
    imageUrl: String? = null
) {
    Box(
        modifier = modifier
    ) {
        if (color != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color, shape = RoundedCornerShape(10.dp))
            )
        } else if (imageUrl != null) {
            AsyncImage(
                model = imageUrl,
                modifier = Modifier.clip(shape = RoundedCornerShape(10.dp)),
                contentDescription = null
            )
        }
    }
}