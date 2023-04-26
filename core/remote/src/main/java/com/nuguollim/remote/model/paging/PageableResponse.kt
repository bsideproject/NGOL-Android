package com.nuguollim.remote.model.paging

import com.google.gson.annotations.SerializedName
import com.nugu.nuguollim.common.data.model.paging.Pageable

data class PageableResponse(
    @SerializedName("sort") val sort: SortResponse,
    @SerializedName("offset") val offset: Long,
    @SerializedName("pageNumber") val pageNumber: Long,
    @SerializedName("pageSize") val pageSize: Long,
    @SerializedName("paged") val paged: Boolean,
    @SerializedName("unpaged") val unpaged: Boolean,
)

fun PageableResponse.asExternalModel() = Pageable(
    sort = sort.asExternalModel(),
    offset = offset,
    pageNumber = pageNumber,
    pageSize = pageSize,
    paged = paged,
    unpaged = unpaged
)