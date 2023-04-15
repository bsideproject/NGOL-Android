package com.nuguollim.data.util

import com.nuguollim.data.state.ResultState
import kotlinx.coroutines.flow.MutableStateFlow

fun <T> mutableResultStateFlow(
    value: ResultState<T> = ResultState.Loading
): MutableStateFlow<ResultState<T>> = MutableStateFlow(ResultState.Loading)