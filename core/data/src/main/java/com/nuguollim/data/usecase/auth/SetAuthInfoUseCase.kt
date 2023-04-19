package com.nuguollim.data.usecase.auth

import com.nuguollim.data.repository.auth.AuthRepository
import com.nuguollim.data.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class SetAuthInfoUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<SetAuthInfoUseCase.Params, Job>() {

    override fun run(params: Params): Job = params.externalScope.launch {
        authRepository.setAuthInfo(
            provideType = params.type.data,
            provideId = params.id.data
        )
    }

    data class Params(
        val type: AuthProvide.Type,
        val id: AuthProvide.Id,
        val externalScope: CoroutineScope
    )

}