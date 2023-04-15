package com.nugu.nuguollim.di

import com.nuguollim.remote.service.auth.AuthService
import com.nuguollim.remote.util.RemoteServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class RemoteServiceModule {

    @Provides
    @Named("baseUrl")
    fun providesBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    fun providesAuthService(
        remoteServiceFactory: RemoteServiceFactory,
        @Named("baseUrl") baseUrl: String
    ): AuthService = remoteServiceFactory.createService(AuthService::class.java, baseUrl)

}