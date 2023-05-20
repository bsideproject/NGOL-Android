package com.nuguollim.remote.model.paper

import com.google.gson.annotations.SerializedName
import com.nugu.nuguollim.common.data.model.paper.Paper

data class AllPaperResponse(
    @SerializedName("message") val message: String,
    @SerializedName("count") val count: Int,
    @SerializedName("data") val data: List<PaperData>
)

fun AllPaperResponse.asExternalModel(): List<Paper> {
    return data.flatMap { dataItem ->
        listOfNotNull(
            dataItem.CRAFT,
            dataItem.TIDY,
            dataItem.FUNNY
        ).flatten().map { paper -> paper.asExternalModel() }
    }
}