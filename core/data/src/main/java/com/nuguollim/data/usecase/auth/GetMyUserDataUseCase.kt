package com.nuguollim.data.usecase.auth

import com.nugu.nuguollim.common.data.model.auth.UserData
import com.nuguollim.data.repository.auth.AuthRepository
import com.nuguollim.data.state.ResultState
import com.nuguollim.data.state.resultStateFlow
import com.nuguollim.data.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyUserDataUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<Unit, Flow<ResultState<UserData>>>() {

    override fun run(params: Unit): Flow<ResultState<UserData>> =
        authRepository.getMyUserData().resultStateFlow

    companion object {
        fun GetMyUserDataUseCase.run(): Flow<ResultState<UserData>> = run(Unit)
    }

}