package com.nugu.nuguollim

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nuguollim.data.usecase.template.RemoveTemplateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyMessageViewModel @Inject constructor(
    private val removeTemplateUseCase: RemoveTemplateUseCase,
) : ViewModel() {

    fun removeTemplate(
        id: Long,
        success: () -> Unit,
        fail: (Throwable) -> Unit
    ) {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable -> fail.invoke(throwable) }) {
            removeTemplateUseCase.run(id)
            success.invoke()
        }
    }

}