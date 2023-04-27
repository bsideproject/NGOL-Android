package com.nuguollim.data.usecase.auth

import com.nugu.nuguollim.common.data.model.auth.TokenData
import com.nuguollim.data.repository.auth.AuthRepository
import com.nuguollim.data.state.ResultState
import com.nuguollim.data.state.resultStateFlow
import com.nuguollim.data.usecase.BaseUseCase
import com.nuguollim.remote.util.toRequestBody
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import org.json.JSONObject
import javax.inject.Inject

class CreateTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<CreateTokenUseCase.Params, Flow<ResultState<TokenData>>>() {

    override fun run(params: Params): Flow<ResultState<TokenData>> =
        authRepository.createToken(params.requestBody).resultStateFlow

    data class Params(
        val type: AuthProvide.Type,
        val id: AuthProvide.Id
    ) {
        val requestBody: RequestBody
            get() = JSONObject()
                .put("providerType", type.data)
                .put("providerId", id.data)
                .toRequestBody()
    }

}