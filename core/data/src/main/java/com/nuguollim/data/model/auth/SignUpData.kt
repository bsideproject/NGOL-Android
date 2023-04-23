package com.nuguollim.data.model.auth

import com.nuguollim.remote.model.auth.SignupResponse

data class SignUpData(
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