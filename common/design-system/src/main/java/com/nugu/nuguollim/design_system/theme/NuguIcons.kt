package com.nugu.nuguollim.design_system.theme

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.nugu.nuguollim.design_system.R

object NuguIcons {

    val BackArrow
        @Composable get() = Icon(
            painter = painterResource(R.drawable.img_back),
            contentDescription = "뒤로가기"
        )

}
