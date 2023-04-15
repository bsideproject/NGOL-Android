package com.nuguollim.remote.util

import com.google.gson.Gson
import com.nuguollim.remote.adapter.FlowCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

class RemoteServiceFactory(
    private vararg val interceptors: Interceptor
) {

    @Singleton
    private val okHttpClient: OkHttpClient
        get() = OkHttpClient.Builder().run {
            interceptors.all { interceptor ->
                addInterceptor(interceptor)
                true
            }
            connectTimeout(10, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            readTimeout(20, TimeUnit.SECONDS)
            build()
        }

    @Singleton
    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .client(okHttpClient)
        .addCallAdapterFactory(FlowCallAdapterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(Gson()))

    fun <T> createService(type: Class<T>, baseUrl: String): T = retrofitBuilder
        .baseUrl(baseUrl)
        .build()
        .create(type)

    companion object {
        const val HTTP_LOGGING_LEVEL_BASIC = 1
        const val HTTP_LOGGING_LEVEL_HEADERS = 2
        const val HTTP_LOGGING_LEVEL_BODY = 3

        fun getHttpLoggingInterceptor(level: Int): HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply {
                when (level) {
                    HTTP_LOGGING_LEVEL_BASIC -> HttpLoggingInterceptor.Level.BASIC
                    HTTP_LOGGING_LEVEL_HEADERS -> HttpLoggingInterceptor.Level.HEADERS
                    HTTP_LOGGING_LEVEL_BODY -> HttpLoggingInterceptor.Level.BODY
                    else -> HttpLoggingInterceptor.Level.NONE
                }.let { setLevel(it) }
            }
    }

}