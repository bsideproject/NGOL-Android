package com.nuguollim.data.repository.seach

import com.nugu.nuguollim.common.data.model.search.target.TemplateTargetData
import com.nuguollim.remote.data_source.search.SearchRemoteDataSource
import com.nuguollim.remote.model.search.target.TemplateTargetResponse.Companion.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource
) : SearchRepository {

    override fun getTemplateTargets(): Flow<List<TemplateTargetData>> =
        searchRemoteDataSource.getTemplateTargets().map { it.data.asExternalModel() }

}