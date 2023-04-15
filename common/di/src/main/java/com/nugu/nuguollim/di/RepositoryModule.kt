package com.nugu.nuguollim.di

import com.nuguollim.data.repository.auth.AuthRepository
import com.nuguollim.data.repository.auth.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository

}