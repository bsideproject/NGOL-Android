package com.nuguollim.remote.adapter

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal class ResponseCallAdapter<T>(
    private val responseType: Type
) : CallAdapter<T, Flow<T>> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<T>): Flow<T> = flow {
        emit(
            suspendCancellableCoroutine { cancellableContinuation ->
                call.enqueue(
                    object : Callback<T> {
                        override fun onResponse(call: Call<T>, response: Response<T>) {
                            cancellableContinuation.resume(response.body()!!)
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