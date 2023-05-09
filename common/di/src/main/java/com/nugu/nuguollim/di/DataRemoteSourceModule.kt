package com.nugu.nuguollim.di

import com.nuguollim.remote.data_source.auth.AuthRemoteDataSource
import com.nuguollim.remote.data_source.auth.AuthRemoteDataSourceImpl
import com.nuguollim.remote.data_source.paper.PaperRemoteDataSource
import com.nuguollim.remote.data_source.paper.PaperRemoteDataSourceImpl
import com.nuguollim.remote.data_source.search.SearchRemoteDataSource
import com.nuguollim.remote.data_source.search.SearchRemoteDataSourceImpl
import com.nuguollim.remote.data_source.template.TemplateRemoteDataSource
import com.nuguollim.remote.data_source.template.TemplateRemoteDataSourceImpl
import com.nuguollim.remote.data_source.terms.TermsRemoteDataSource
import com.nuguollim.remote.data_source.terms.TermsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataRemoteSourceModule {

    @Binds
    fun bindsAuthRemoteDataSource(authRemoteDataSource: AuthRemoteDataSourceImpl): AuthRemoteDataSource

    @Binds
    fun bindsTermsRemoteDataSource(termsRemoteDataSource: TermsRemoteDataSourceImpl): TermsRemoteDataSource

    @Binds
    fun bindsTemplateRemoteDataSource(templateRemoteDataSource: TemplateRemoteDataSourceImpl): TemplateRemoteDataSource

    @Binds
    fun bindsSearchRemoteDataSource(searchRemoteDataSource: SearchRemoteDataSourceImpl): SearchRemoteDataSource

    @Binds
    fun bindsPaperRemoteDataSource(paperRemoteDataSource: PaperRemoteDataSourceImpl): PaperRemoteDataSource

}