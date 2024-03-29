package com.nuguollim.data.usecase.auth

import com.nugu.nuguollim.common.data.model.auth.SignUpData
import com.nuguollim.data.repository.auth.AuthRepository
import com.nuguollim.data.state.ResultState
import com.nuguollim.data.state.resultStateFlow
import com.nuguollim.data.usecase.BaseUseCase
import com.nuguollim.remote.util.toRequestBody
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<SignUpUseCase.Params, Flow<ResultState<SignUpData>>>() {

    override fun run(params: Params): Flow<ResultState<SignUpData>> =
        authRepository.signup(params.requestBody).resultStateFlow

    data class Params(
        val type: AuthProvide.Type,
        val id: AuthProvide.Id,
        val name: AuthProvide.Name,
        val terms: List<AuthProvide.Terms>
    ) {
        val requestBody: RequestBody
            get() = JSONObject()
                .put("providerType", type.data)
                .put("providerId", id.data)
                .put("nickname", name.data)
                .put("terms", JSONArray(terms.map { it.data }.toLongArray()))
                .toRequestBody()
    }

}