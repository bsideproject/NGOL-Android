package com.nuguollim.data.repository.paper

import com.nuguollim.remote.data_source.paper.PaperRemoteDataSourceImpl
import com.nuguollim.remote.model.paper.asExternalModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PaperRepositoryImpl @Inject constructor(
    private val paperRemoteDataSource: PaperRemoteDataSourceImpl
) : PaperRepository {
    override fun getPapers() =
        paperRemoteDataSource.getPapers()
            .map { it.asExternalModel() }
}