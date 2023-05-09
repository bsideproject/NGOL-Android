package com.nugu.nuguollim.di

import com.nuguollim.remote.service.auth.AuthService
import com.nuguollim.remote.service.paper.PaperService
import com.nuguollim.remote.service.search.SearchService
import com.nuguollim.remote.service.template.TemplateService
import com.nuguollim.remote.service.terms.TermsService
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

    @Provides
    fun providesTermsService(
        remoteServiceFactory: RemoteServiceFactory,
        @Named("baseUrl") baseUrl: String
    ): TermsService = remoteServiceFactory.createService(TermsService::class.java, baseUrl)

    @Provides
    fun providesTemplateService(
        remoteServiceFactory: RemoteServiceFactory,
        @Named("baseUrl") baseUrl: String
    ): TemplateService = remoteServiceFactory.createService(TemplateService::class.java, baseUrl)

    @Provides
    fun providesSearchService(
        remoteServiceFactory: RemoteServiceFactory,
        @Named("baseUrl") baseUrl: String
    ): SearchService = remoteServiceFactory.createService(SearchService::class.java, baseUrl)

    @Provides
    fun providesPaperService(
        remoteServiceFactory: RemoteServiceFactory,
        @Named("baseUrl") baseUrl: String
    ): PaperService = remoteServiceFactory.createService(PaperService::class.java, baseUrl)

}