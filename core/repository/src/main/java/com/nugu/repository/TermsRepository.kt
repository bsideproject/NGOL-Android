package com.nugu.repository

import com.nugu.terms.RetrofitTermsNetwork
import com.nugu.terms.model.asExternalModel
import com.nuguollim.data.model.Terms
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TermsRepository @Inject constructor(
    private val network: RetrofitTermsNetwork
) {
    fun getTermsList(): Flow<List<Terms>> = flow {
        emit(
            network.getTermsList().data
                .map { it.asExternalModel() }
                .filter { it.active }
        )
    }.flowOn(Dispatchers.IO)
}