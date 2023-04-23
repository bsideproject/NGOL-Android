package com.nuguollim.data.usecase.auth

import com.nugu.config.AuthConfig
import com.nuguollim.data.repository.auth.AuthRepository
import javax.inject.Inject

class SetProvideTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(authProvide: AuthProvide.Token) {
        AuthConfig.token = authProvide.data
        authRepository.setProvideToken(authProvide.data)
    }

}