package com.nuguollim.data.usecase

import com.nuguollim.data.state.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

abstract class BaseUseCase<in Params, out Type> {

    abstract fun run(params: Params): Type

    operator fun invoke(params: Params) = run(params)

    protected val <T> Flow<T>.resultStateFlow: Flow<ResultState<T>>
        get() = map<T, ResultState<T>> { ResultState.Success(it) }
            .onStart { emit(ResultState.Loading) }
            .catch { emit(ResultState.Error(it)) }

}