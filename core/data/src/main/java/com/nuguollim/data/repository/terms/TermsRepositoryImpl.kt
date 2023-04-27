package com.nuguollim.data.repository.terms

import com.nugu.nuguollim.common.data.model.terms.Terms
import com.nuguollim.remote.data_source.terms.TermsRemoteDataSource
import com.nuguollim.remote.model.terms.asExternalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TermsRepositoryImpl @Inject constructor(
    private val termsRemoteDataSource: TermsRemoteDataSource
) : TermsRepository {

    override fun getTermsList(
        active: Boolean?,
        termsTitle: String?,
        termsType: String?
    ): Flow<List<Terms>> =
        termsRemoteDataSource.getTermsList(active, termsTitle, termsType)
            .map { it.asExternalModel().data }
            .map { list -> list.filter { it.active } }
            .flowOn(Dispatchers.IO)
}