package com.nuguollim.data.repository.seach

import com.nugu.nuguollim.common.data.model.search.target.TemplateTargetData
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun getTemplateTargets(): Flow<List<TemplateTargetData>>

}