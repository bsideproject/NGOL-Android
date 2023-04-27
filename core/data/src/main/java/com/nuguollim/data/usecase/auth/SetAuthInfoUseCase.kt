package com.nuguollim.data.usecase.auth

import com.nuguollim.data.repository.auth.AuthRepository
import javax.inject.Inject

class SetAuthInfoUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(params: Params) {
        authRepository.setAuthInfo(
            provideType = params.type.data,
            provideId = params.id.data,
        )
    }

    data class Params(
        val type: AuthProvide.Type,
        val id: AuthProvide.Id,
    )

}