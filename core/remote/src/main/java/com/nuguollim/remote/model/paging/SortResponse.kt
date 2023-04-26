package com.nuguollim.remote.model.paging

import com.google.gson.annotations.SerializedName
import com.nugu.nuguollim.common.data.model.paging.Sort

data class SortResponse(
    @SerializedName("empty") val empty: Boolean,
    @SerializedName("sorted") val sorted: Boolean,
    @SerializedName("unsorted") val unsorted: Boolean,
)

fun SortResponse.asExternalModel() = Sort(
    empty = empty,
    sorted = sorted,
    unsorted = unsorted
)