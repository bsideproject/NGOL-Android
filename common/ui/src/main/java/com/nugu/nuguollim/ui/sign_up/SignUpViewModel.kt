package com.nugu.nuguollim.ui.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nugu.repository.TermsRepository
import com.nuguollim.data.model.auth.SignUpData
import com.nuguollim.data.usecase.auth.AuthProvide
import com.nuguollim.data.usecase.auth.SignUpUseCase
import com.nuguollim.data.util.mutableResultStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val termsRepository: TermsRepository,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    val termsList = termsRepository.getTermsList().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _signUpState = mutableResultStateFlow<SignUpData>()
    val signUpState = _signUpState.asStateFlow()

    fun signUp(
        type: AuthProvide.Type,
        id: AuthProvide.Id,
        name: AuthProvide.Name
    ) {
        signUpUseCase.run(SignUpUseCase.Params(type, id, name))
            .onEach { _signUpState.value = it }
            .launchIn(viewModelScope)
    }

}