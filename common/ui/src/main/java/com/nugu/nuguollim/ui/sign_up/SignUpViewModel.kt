package com.nugu.nuguollim.ui.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nuguollim.data.model.auth.SignUpData
import com.nuguollim.data.model.auth.TokenData
import com.nuguollim.data.repository.terms.TermsRepositoryImpl
import com.nuguollim.data.usecase.auth.AuthProvide
import com.nuguollim.data.usecase.auth.CreateTokenUseCase
import com.nuguollim.data.usecase.auth.SetAuthInfoUseCase
import com.nuguollim.data.usecase.auth.SetProvideTokenUseCase
import com.nuguollim.data.usecase.auth.SignUpUseCase
import com.nuguollim.data.util.mutableResultStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val termsRepository: TermsRepositoryImpl,
    private val signUpUseCase: SignUpUseCase,
    private val createTokenUseCase: CreateTokenUseCase,
    private val setAuthInfoUseCase: SetAuthInfoUseCase,
    private val setProvideTokenUseCase: SetProvideTokenUseCase
) : ViewModel() {
    val termsList = termsRepository.getTermsList().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    /** 로그인 요청 상태 */
    private val _loginState = mutableResultStateFlow<TokenData>()
    val loginState = _loginState.asStateFlow()

    /** 회원가입 상태 */
    private val _signUpState = mutableResultStateFlow<SignUpData>()
    val signUpState = _signUpState.asStateFlow()

    fun signUp(
        type: AuthProvide.Type,
        id: AuthProvide.Id,
        name: AuthProvide.Name,
        terms: List<AuthProvide.Terms>
    ) {
        signUpUseCase.run(SignUpUseCase.Params(type, id, name, terms))
            .onEach { _signUpState.value = it }
            .launchIn(viewModelScope)
    }

    fun login(type: AuthProvide.Type, id: AuthProvide.Id, ) {
        createTokenUseCase.run(CreateTokenUseCase.Params(type, id))
            .onEach { _loginState.value = it }
            .launchIn(viewModelScope)
    }

    fun setAuthInfo(type: AuthProvide.Type, id: AuthProvide.Id) {
        viewModelScope.launch {
            setAuthInfoUseCase.invoke(
                SetAuthInfoUseCase.Params(
                    type = type,
                    id = id,
                )
            )
        }
    }

    fun setToken(token: AuthProvide.Token) {
        viewModelScope.launch {
            setProvideTokenUseCase.invoke(token)
        }
    }

}