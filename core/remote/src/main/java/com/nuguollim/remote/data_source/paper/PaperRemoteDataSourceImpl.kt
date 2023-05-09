package com.nuguollim.remote.data_source.paper

import com.nuguollim.remote.model.paper.AllPaperResponse
import com.nuguollim.remote.service.paper.PaperService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PaperRemoteDataSourceImpl @Inject constructor(
    private val paperService: PaperService
): PaperRemoteDataSource {

    override fun getPapers(): Flow<AllPaperResponse> =
        paperService.getPapers()
}