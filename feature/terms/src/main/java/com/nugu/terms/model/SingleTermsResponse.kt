package com.nugu.terms.model

import com.google.gson.annotations.SerializedName
import com.nuguollim.data.model.SingleTerms

data class SingleTermsResponse(
    @SerializedName("message") val message: String,
    @SerializedName("count") val count: Long,
    @SerializedName("data") val data: TermsResponse,
)

fun SingleTermsResponse.asExternalModel() = SingleTerms(
    message = message,
    count = count,
    data = data.asExternalModel()
)
