package com.nuguollim.data.state

sealed interface ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>
    data class Error(val error: Throwable? = null) : ResultState<Nothing>
    object Loading : ResultState<Nothing>
}