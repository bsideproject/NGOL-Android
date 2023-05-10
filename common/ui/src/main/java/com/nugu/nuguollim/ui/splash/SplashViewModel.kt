package com.nugu.nuguollim.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nuguollim.data.usecase.auth.GetFirstInitInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getFirstInitInfoUseCase: GetFirstInitInfoUseCase,
) : ViewModel() {

    fun getIsFirstInit(isFirstInit: (Boolean) -> Unit) {
        viewModelScope.launch {
            isFirstInit.invoke(getFirstInitInfoUseCase.run())
        }
    }

}