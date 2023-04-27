package com.nugu.ui_core

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce

interface SingleClickEventListener {
    fun onClick(action: () -> Unit)
}

@Composable
fun <T> singleClick(content: @Composable (SingleClickEventListener) -> T): T {
    val debounceState = remember {
        MutableSharedFlow<() -> Unit>(
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    }

    val result = content.invoke(
        object : SingleClickEventListener {
            override fun onClick(action: () -> Unit) {
                debounceState.tryEmit(action)
            }
        }
    )

    LaunchedEffect(Unit) {
        debounceState
            .debounce(300)
            .collect { it.invoke() }
    }

    return result
}

fun Modifier.singleClick(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    singleClick { singleClickListener ->
        clickable(
            enabled = enabled,
            onClickLabel = onClickLabel,
            onClick = { singleClickListener.onClick { onClick.invoke() } },
            role = role,
            indication = LocalIndication.current,
            interactionSource = remember { MutableInteractionSource() }
        )
    }
}