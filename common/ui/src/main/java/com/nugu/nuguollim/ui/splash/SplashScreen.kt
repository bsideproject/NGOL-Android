package com.nugu.nuguollim.ui.splash

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.nugu.nuguollim.design_system.component.SplashAnimation

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    onFinish: (Boolean) -> Unit = {},
) {
    SplashAnimation {
        viewModel.getIsFirstInit { onFinish.invoke(it) }
    }
}