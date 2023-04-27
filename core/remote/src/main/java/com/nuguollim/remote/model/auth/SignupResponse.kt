package com.nuguollim.remote.model.auth

import com.nugu.nuguollim.common.data.model.auth.SignUpData

data class SignupResponse(
    val id: Int,
    val providerType: String,
    val providerId: String,
    val nickname: String,
) {
    companion object {
        fun SignupResponse.asExternalModel(): SignUpData = SignUpData(
            id = id,
            providerType = providerType,
            providerId = providerId,
            nickname = nickname,
        )
    }
}