package com.nugu.ui_core

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput

fun Modifier.addFocusCleaner(
    focusManager: FocusManager,
    onFocusClear: () -> Unit
): Modifier = pointerInput(Unit) {
    detectTapGestures {
        onFocusClear.invoke()
        focusManager.clearFocus()
    }
}