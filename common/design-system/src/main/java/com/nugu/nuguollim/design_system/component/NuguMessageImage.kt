package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage

@Composable
fun NuguMessageImage(
    modifier: Modifier = Modifier,
    color: Color? = null,
    imageUrl: String? = null
) {
    Box(
        modifier = modifier.background(Color.Transparent)
    ) {
        if (color != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color)
            )
        } else if (imageUrl != null) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = imageUrl,
                contentDescription = null
            )
        }
    }
}