package com.nuguollim.data.usecase.auth

import com.nuguollim.data.model.auth.AuthInfo
import com.nuguollim.data.repository.auth.AuthRepository
import com.nuguollim.data.state.ResultState
import com.nuguollim.data.state.resultStateFlow
import com.nuguollim.data.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAuthInfoUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<Unit, Flow<ResultState<AuthInfo>>>() {

    override fun run(params: Unit): Flow<ResultState<AuthInfo>> =
        authRepository.getAuthInfo().resultStateFlow

    companion object {
        fun GetAuthInfoUseCase.run(): Flow<ResultState<AuthInfo>> = run(Unit)
    }

}