package com.nuguollim.remote.util

import com.nugu.exception.ResponseException
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class ResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (!response.isSuccessful) {
            runCatching {
                val responseBody = JSONObject(response.body!!.string())
                val errorMessage = responseBody.getStringOrNull("message")
                val errorCode = responseBody.getStringOrNull("errorCode")

                Pair(errorMessage, errorCode)
            }.onSuccess { (errorMessage, errorCode) ->
                throw ResponseException(errorMessage, errorCode)
            }.onFailure { throw IOException(it) }
        }

        return response
    }

    private fun JSONObject.getStringOrNull(key: String): String? =
        if (has(key)) getString(key) else null
}