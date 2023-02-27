package com.nuguollim.remote.adapter

import kotlinx.coroutines.flow.Flow
import retrofit2.CallAdapter
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

internal class FlowCallAdapterFactory private constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Flow::class.java) {
            return null
        }
        check(returnType is ParameterizedType) { "" }

        val responseType = getParameterUpperBound(0, returnType)
        val rawFlowType = getRawType(returnType)

        return if (rawFlowType == Response::class.java) {
            check(responseType is ParameterizedType) { "" }

            ResponseCallAdapter<Any>(getParameterUpperBound(0, responseType))
        } else {
            BodyCallAdapter<Any>(responseType)
        }
    }

    companion object {
        fun create() = FlowCallAdapterFactory()
    }

}