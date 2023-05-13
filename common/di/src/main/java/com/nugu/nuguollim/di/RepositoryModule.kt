package com.nugu.nuguollim.di

import com.nuguollim.data.repository.auth.AuthRepository
import com.nuguollim.data.repository.auth.AuthRepositoryImpl
import com.nuguollim.data.repository.seach.SearchRepository
import com.nuguollim.data.repository.seach.SearchRepositoryImpl
import com.nuguollim.data.repository.splash.SplashRepository
import com.nuguollim.data.repository.splash.SplashRepositoryImpl
import com.nuguollim.data.repository.template.TemplateRepository
import com.nuguollim.data.repository.template.TemplateRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindsSearchRepository(searchRepository: SearchRepositoryImpl): SearchRepository

    @Binds
    fun bindsTemplateRepository(templateRepository: TemplateRepositoryImpl): TemplateRepository

    @Binds
    fun bindsSplashRepository(splashRepository: SplashRepositoryImpl): SplashRepository

}