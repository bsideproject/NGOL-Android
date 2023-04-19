package com.nuguollim.data.model.auth

import com.nuguollim.remote.model.auth.TokenResponse

data class TokenData(val token: String) {
    companion object {
        fun TokenResponse.asExternalModel(): TokenData = TokenData(token)
    }
}