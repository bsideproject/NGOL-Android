package com.nuguollim.remote.data_source.search

import com.nuguollim.remote.model.search.target.AllTemplateTargetResponse
import com.nuguollim.remote.service.search.SearchService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val searchService: SearchService
) : SearchRemoteDataSource {

    override fun getTemplateTargets(): Flow<AllTemplateTargetResponse> =
        searchService.getTemplateTargets()

}