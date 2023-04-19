package com.nuguollim.remote.model.terms

import com.google.gson.annotations.SerializedName
import com.nugu.nuguollim.common.data.model.terms.AllTerms

data class AllTermsResponse(
    @SerializedName("message") val message: String,
    @SerializedName("count") val count: Long,
    @SerializedName("data") val data: List<TermsResponse>,
)

fun AllTermsResponse.asExternalModel() = AllTerms(
    message = message,
    count = count,
    data = data.map { it.asExternalModel() }
)
