package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun NuguLoadingProgressDialog(
    onDismissRequest: () -> Unit = {}
) {
    Dialog(onDismissRequest) {
        val loadingLottie by rememberLottieComposition(LottieCompositionSpec.Asset("loading.json"))
        val lottieProgress by animateLottieCompositionAsState(
            composition = loadingLottie,
            iterations = LottieConstants.IterateForever
        )
        LottieAnimation(
            composition = loadingLottie,
            progress = { lottieProgress },
            modifier = Modifier.fillMaxSize()
        )
    }
}