package com.nuguollim.remote.model.paper

import com.google.gson.annotations.SerializedName
import com.nugu.nuguollim.common.data.model.paper.Paper
import com.nugu.nuguollim.common.data.model.paper.PaperTheme

data class PaperDetailResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("theme") val theme: String,
    @SerializedName("thumbImg") val thumbImg: String
)

fun PaperDetailResponse.asExternalModel() = Paper(
    id = id,
    img = img,
    name = name,
    theme = PaperTheme.valueOf(theme),
    thumbImg = thumbImg
)