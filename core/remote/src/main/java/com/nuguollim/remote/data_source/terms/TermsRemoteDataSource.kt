package com.nuguollim.remote.data_source.terms

import com.nuguollim.remote.model.terms.AllTermsResponse
import kotlinx.coroutines.flow.Flow

interface TermsRemoteDataSource {

    fun getTermsList(
        active: Boolean? = null,
        termsTitle: String? = null,
        termsType: String? = null
    ): Flow<AllTermsResponse>
}