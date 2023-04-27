package com.nuguollim.data.usecase.search

import com.nugu.nuguollim.common.data.model.search.target.TemplateTargetData
import com.nuguollim.data.repository.seach.SearchRepository
import com.nuguollim.data.state.ResultState
import com.nuguollim.data.state.resultStateFlow
import com.nuguollim.data.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTemplateTargetsUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) : BaseUseCase<Unit, Flow<ResultState<List<TemplateTargetData>>>>() {

    override fun run(params: Unit): Flow<ResultState<List<TemplateTargetData>>> =
        searchRepository.getTemplateTargets().resultStateFlow

    fun run(): Flow<ResultState<List<TemplateTargetData>>> = run(Unit)

}