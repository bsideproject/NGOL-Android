package com.nuguollim.remote.data_source.paper

import com.nuguollim.remote.model.paper.AllPaperResponse
import kotlinx.coroutines.flow.Flow

interface PaperRemoteDataSource {
    fun getPapers(): Flow<AllPaperResponse>
}