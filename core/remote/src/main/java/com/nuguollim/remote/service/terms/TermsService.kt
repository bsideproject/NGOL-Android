package com.nuguollim.remote.service.terms

import com.nuguollim.remote.model.terms.AllTermsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface TermsService {
    @GET(value = "terms")
    fun getTermsList(
        @Query("active") active: Boolean? = null,
        @Query("termsTitle") termsTitle: String? = null,
        @Query("termsType") termsType: String? = null
    ): Flow<AllTermsResponse>
}