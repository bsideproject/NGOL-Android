package com.nugu.nuguollim.di

import com.nuguollim.remote.util.RemoteServiceFactory
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
    fun providesHttpLoggingInterceptor(): Interceptor =
        RemoteServiceFactory.getHttpLoggingInterceptor(RemoteServiceFactory.HTTP_LOGGING_LEVEL_BODY)

    @Provides
    fun providesRemoteServiceFactory(
        @Named("httpLogging") httpLogging: Interceptor
    ): RemoteServiceFactory = RemoteServiceFactory(httpLogging)

}