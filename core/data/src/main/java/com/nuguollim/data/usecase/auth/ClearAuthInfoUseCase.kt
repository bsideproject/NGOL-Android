package com.nuguollim.data.usecase.auth

import com.nuguollim.data.repository.auth.AuthRepository
import javax.inject.Inject

class ClearAuthInfoUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke() {
        authRepository.clearAuthInfo()
    }

}