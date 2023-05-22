package com.nuguollim.data.usecase.terms

import com.nugu.nuguollim.common.data.model.terms.Terms
import com.nuguollim.data.repository.terms.TermsRepository
import com.nuguollim.data.state.ResultState
import com.nuguollim.data.state.resultStateFlow
import com.nuguollim.data.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTermsUseCase @Inject constructor(
    private val termsRepository: TermsRepository
) : BaseUseCase<String, Flow<ResultState<List<Terms>>>>() {

    override fun run(params: String): Flow<ResultState<List<Terms>>> = termsRepository.getTermsList(
        active = true,
        termsTitle = params,
        termsType = null
    ).resultStateFlow

}