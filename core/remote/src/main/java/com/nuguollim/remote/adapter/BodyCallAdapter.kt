package com.nuguollim.remote.adapter

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import kotlin.coroutines.resumeWithException

internal class BodyCallAdapter<T>(
    private val responseType: Type
) : CallAdapter<T, Flow<T>> {

    override fun responseType(): Type = responseType

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun adapt(call: Call<T>): Flow<T> = flow {
        emit(
            suspendCancellableCoroutine { cancellableContinuation ->
                call.clone().enqueue(
                    object : Callback<T> {
                        override fun onResponse(call: Call<T>, response: Response<T>) {
                            cancellableContinuation.resume(response.body()!!) {
                                cancellableContinuation.resumeWithException(it)
                            }
                        }

                        override fun onFailure(call: Call<T>, t: Throwable) {
                            cancellableContinuation.resumeWithException(t)
                        }
                    }
                )
                cancellableContinuation.invokeOnCancellation { call.cancel() }
            }
        )
    }

}