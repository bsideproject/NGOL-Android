package com.nugu.nuguollim.common.data.model.template

import com.nugu.nuguollim.common.data.model.paging.Paging

data class AllTemplate(
    val message: String,
    val count: Long,
    val data: Paging<Template>
)
