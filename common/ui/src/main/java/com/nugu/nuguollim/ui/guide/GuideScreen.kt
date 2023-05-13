package com.nugu.nuguollim.ui.guide

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.nugu.onboard.component.NuguGuideScreen

@Composable
fun GuideScreen(
    viewModel: GuideViewModel = hiltViewModel(),
    onStart: () -> Unit = {},
) {
    NuguGuideScreen {
        viewModel.setInit(
            isInit = false,
            onComplete = onStart
        )
    }
}