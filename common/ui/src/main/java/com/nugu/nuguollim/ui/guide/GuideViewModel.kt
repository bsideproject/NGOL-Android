package com.nugu.nuguollim.ui.guide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nuguollim.data.usecase.auth.SetFirstInitInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuideViewModel @Inject constructor(
    private val setFirstInitInfoUseCase: SetFirstInitInfoUseCase
) : ViewModel() {

    fun setInit(isInit: Boolean, onComplete: () -> Unit) {
        viewModelScope.launch {
            setFirstInitInfoUseCase.run(isInit)
            onComplete.invoke()
        }
    }

}