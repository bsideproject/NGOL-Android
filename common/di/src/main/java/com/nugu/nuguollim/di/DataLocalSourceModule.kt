package com.nugu.nuguollim.di

import com.nugu.data_store.data_source.auth.AuthLocalDataSource
import com.nugu.data_store.data_source.auth.AuthLocalDataSourceImpl
import com.nugu.data_store.data_source.splash.SplashLocalDataSource
import com.nugu.data_store.data_source.splash.SplashLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataLocalSourceModule {

    @Binds
    fun bindsAuthRemoteDataSource(authLocalDataSource: AuthLocalDataSourceImpl): AuthLocalDataSource

    @Binds
    fun bindsSplashLocalDataSource(splashLocalDataSource: SplashLocalDataSourceImpl): SplashLocalDataSource

}