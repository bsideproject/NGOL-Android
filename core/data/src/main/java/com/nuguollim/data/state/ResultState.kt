package com.nuguollim.data.state

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface ResultState<out T> {

    val successData: T get() = (this as Success<T>).data
    val errorData: Throwable? get() = (this as Error).error

    data class Success<T>(val data: T) : ResultState<T>
    data class Error(val error: Throwable? = null) : ResultState<Nothing>
    object Loading : ResultState<Nothing>

}

val <T> Flow<T>.resultStateFlow: Flow<ResultState<T>>
    get() = map<T, ResultState<T>> { ResultState.Success(it) }
        .onStart { emit(ResultState.Loading) }
        .catch { emit(ResultState.Error(it)) }