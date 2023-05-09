package com.nuguollim.data.repository.paper

import com.nugu.nuguollim.common.data.model.paper.Paper
import kotlinx.coroutines.flow.Flow

interface PaperRepository {

    fun getPapers(): Flow<List<Paper>>
}