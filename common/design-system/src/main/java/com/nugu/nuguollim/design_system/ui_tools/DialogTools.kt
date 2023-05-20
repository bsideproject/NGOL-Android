package com.nugu.nuguollim.design_system.ui_tools

import android.view.View
import android.view.WindowManager
import androidx.compose.runtime.Stable
import androidx.compose.ui.window.DialogWindowProvider

@Stable
fun View.dimClear() {
    (parent as DialogWindowProvider).window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
}