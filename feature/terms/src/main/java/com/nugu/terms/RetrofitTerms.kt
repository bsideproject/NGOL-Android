package com.nugu.terms

import com.nugu.remote.BuildConfig
import com.nugu.terms.model.AllTermsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton


private const val RetrofitBaseUrl = BuildConfig.BASE_URL

private interface RetrofitTerms {
    @GET(value = "terms")
    suspend fun getTermsList(
        @Query("active") active: Boolean? = null,
        @Query("termsTitle") termsTitle: String? = null,
        @Query("termsType") termsType: String? = null
    ): AllTermsResponse
}

@Singleton
class RetrofitTermsNetwork @Inject constructor(

) {

    private val networkApi = Retrofit.Builder()
        .baseUrl(RetrofitBaseUrl)
        .callFactory(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor()
                        .apply {
                            if (BuildConfig.DEBUG) {
                                setLevel(HttpLoggingInterceptor.Level.BODY)
                            }
                        },
                )
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitTerms::class.java)

    suspend fun getTermsList(
        active: Boolean? = null,
        termsTitle: String? = null,
        termsType: String? = null
    ): AllTermsResponse =
        networkApi.getTermsList(active, termsTitle, termsType)
}