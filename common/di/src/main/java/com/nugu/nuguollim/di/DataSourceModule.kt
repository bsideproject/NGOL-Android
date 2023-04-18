package com.nugu.nuguollim.di

import com.nuguollim.remote.data_source.auth.AuthRemoteDataSource
import com.nuguollim.remote.data_source.auth.AuthRemoteDataSourceImpl
import com.nuguollim.remote.data_source.terms.TermsRemoteDataSource
import com.nuguollim.remote.data_source.terms.TermsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindsAuthRemoteDataSource(authRemoteDataSource: AuthRemoteDataSourceImpl): AuthRemoteDataSource

    @Binds
    fun bindsTermsRemoteDataSource(termsRemoteDataSource: TermsRemoteDataSourceImpl): TermsRemoteDataSource

}