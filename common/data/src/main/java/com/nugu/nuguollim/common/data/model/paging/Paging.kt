package com.nugu.nuguollim.common.data.model.paging


data class Paging<T>(
    val content: List<T>,
    val pageable: Pageable,
    val size: Int,
    val number: Int,
    val sort: Sort,
    val first: Boolean,
    val last: Boolean,
    val numberOfElements: Int,
    val empty: Boolean
)
