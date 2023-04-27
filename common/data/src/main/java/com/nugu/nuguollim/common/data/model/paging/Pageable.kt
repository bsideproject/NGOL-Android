package com.nugu.nuguollim.common.data.model.paging

data class Pageable(
    val sort: Sort,
    val offset: Long,
    val pageNumber: Long,
    val pageSize: Long,
    val paged: Boolean,
    val unpaged: Boolean,
)
