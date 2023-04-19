package com.nuguollim.remote.data_source.terms

import com.nuguollim.remote.model.terms.AllTermsResponse
import com.nuguollim.remote.service.terms.TermsService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TermsRemoteDataSourceImpl @Inject constructor(
    private val termsService: TermsService
) : TermsRemoteDataSource {

    override fun getTermsList(
        active: Boolean?,
        termsTitle: String?,
        termsType: String?
    ): Flow<AllTermsResponse> =
        termsService.getTermsList(active, termsTitle, termsType)
}