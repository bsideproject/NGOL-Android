package com.nugu.nuguollim.di

import com.nuguollim.remote.util.RemoteServiceFactory
import com.nuguollim.remote.util.ResponseInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class InterceptorModule {

    @Provides
    @Named("httpLogging")
    fun providesHttpLoggingInterceptor(): Interceptor? =
        if (BuildConfig.DEBUG) {
            RemoteServiceFactory.getHttpLoggingInterceptor(RemoteServiceFactory.HTTP_LOGGING_LEVEL_BODY)
        } else {
            null
        }

    @Provides
    @Named("responseInterceptor")
    fun providesResponseInterceptor(): Interceptor = ResponseInterceptor()

    @Provides
    fun providesRemoteServiceFactory(
        @Named("httpLogging") httpLogging: Interceptor?,
        @Named("responseInterceptor") responseInterceptor: Interceptor
    ): RemoteServiceFactory = RemoteServiceFactory(httpLogging, responseInterceptor)

}