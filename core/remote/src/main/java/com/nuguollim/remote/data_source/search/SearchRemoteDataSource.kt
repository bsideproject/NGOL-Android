package com.nuguollim.remote.data_source.search

import com.nuguollim.remote.model.search.target.AllTemplateTargetResponse
import kotlinx.coroutines.flow.Flow

interface SearchRemoteDataSource {

    fun getTemplateTargets(): Flow<AllTemplateTargetResponse>

}