package com.nuguollim.remote.model.paper

import com.google.gson.annotations.SerializedName

data class PaperData(
    @SerializedName("CRAFT") val CRAFT: List<PaperDetailResponse>?,
    @SerializedName("TIDY") val TIDY: List<PaperDetailResponse>?,
    @SerializedName("FUNNY") val FUNNY: List<PaperDetailResponse>?
)