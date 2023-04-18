package com.nuguollim.data.repository.terms

import com.nugu.nuguollim.common.data.model.terms.Terms
import kotlinx.coroutines.flow.Flow

interface TermsRepository {

    fun getTermsList(
        active: Boolean? = null,
        termsTitle: String? = null,
        termsType: String? = null
    ): Flow<List<Terms>>
}