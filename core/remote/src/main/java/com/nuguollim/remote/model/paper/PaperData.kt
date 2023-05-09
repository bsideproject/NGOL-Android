package com.nuguollim.remote.model.paper

import com.google.gson.annotations.SerializedName

data class PaperData(
    @SerializedName("FANCY") val FANCY: List<PaperDetailResponse>?,
    @SerializedName("SERIOUS") val SERIOUS: List<PaperDetailResponse>?,
    @SerializedName("SIMPLE") val SIMPLE: List<PaperDetailResponse>?
)