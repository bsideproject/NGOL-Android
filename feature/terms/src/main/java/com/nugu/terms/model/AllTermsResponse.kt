package com.nugu.terms.model

import com.google.gson.annotations.SerializedName
import com.nuguollim.data.model.AllTerms

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
