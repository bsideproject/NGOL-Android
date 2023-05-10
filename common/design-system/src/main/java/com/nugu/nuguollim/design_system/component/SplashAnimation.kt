package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

private const val ANIMATION_PROGRESS_MAX = 1.0f

@Composable
fun SplashAnimation(
    onFinish: () -> Unit = {}
) {
    val loadingLottie by rememberLottieComposition(LottieCompositionSpec.Asset("splash.json"))
    val lottieProgress by animateLottieCompositionAsState(
        composition = loadingLottie
    )
    LottieAnimation(
        composition = loadingLottie,
        progress = { lottieProgress },
        modifier = Modifier.fillMaxSize()
    )

    if (lottieProgress == ANIMATION_PROGRESS_MAX) {
        onFinish.invoke()
    }
}