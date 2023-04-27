package com.nuguollim.remote.model.auth

import com.nugu.nuguollim.common.data.model.auth.TokenData

data class TokenResponse(val accessToken: String) {
    companion object {
        fun TokenResponse.asExternalModel(): TokenData = TokenData(accessToken)
    }
}