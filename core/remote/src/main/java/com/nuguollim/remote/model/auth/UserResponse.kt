package com.nuguollim.remote.model.auth

import com.nugu.nuguollim.common.data.model.auth.UserData

data class UserResponse(
    val id: Long,
    val providerType: String,
    val providerId: String,
    val username: String,
    val nickname: String,
) {
    companion object {
        fun UserResponse.asExternalModel(): UserData =
            UserData(
                id = id,
                providerType = providerType,
                providerId = providerId,
                username = username,
                nickname = nickname,
            )
    }
}