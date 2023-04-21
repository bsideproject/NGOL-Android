package com.nugu.nuguollim.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nugu.social_login.login.GoogleLogin
import com.nugu.social_login.login.KakaoLogin
import com.nugu.social_login.login.NaverLogin
import com.nugu.social_login.login.SocialLoginFactory
import com.nuguollim.data.model.auth.TokenData
import com.nuguollim.data.state.ResultState
import com.nuguollim.data.usecase.auth.AuthProvide
import com.nuguollim.data.usecase.auth.CreateTokenUseCase
import com.nuguollim.data.usecase.auth.GetAuthInfoUseCase
import com.nuguollim.data.usecase.auth.GetAuthInfoUseCase.Companion.run
import com.nuguollim.data.usecase.auth.SetAuthInfoUseCase
import com.nuguollim.data.util.mutableResultStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val socialLoginFactory: SocialLoginFactory,
    private val createTokenUseCase: CreateTokenUseCase,
    private val setAuthInfoUseCase: SetAuthInfoUseCase,
    getAuthInfoUseCase: GetAuthInfoUseCase
) : ViewModel() {
    /** 소셜 로그인 */
    val kakaoLogin: KakaoLogin = socialLoginFactory.createKakaoLogin()
    val naverLogin: NaverLogin = socialLoginFactory.createNaverLogin()
    val googleLogin: GoogleLogin = socialLoginFactory.createGoogleLogin()

    val localAuthInfo = getAuthInfoUseCase.run().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ResultState.Loading
    )

    /** 로그인 요청 상태 */
    private val _loginState = mutableResultStateFlow<TokenData>()
    val loginState = _loginState.asStateFlow()

    private fun login(type: AuthProvide.Type, id: AuthProvide.Id) {
        createTokenUseCase.run(CreateTokenUseCase.Params(type, id))
            .onEach { _loginState.value = it }
            .launchIn(viewModelScope)
    }

    fun saveLocalAuthInfoAndLogin(type: AuthProvide.Type, id: AuthProvide.Id) {
        setAuthInfoUseCase.run(SetAuthInfoUseCase.Params(type, id, viewModelScope))
        login(type, id)
    }

}