package com.nugu.nuguollim

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nugu.nuguollim.common.data.model.template.Writing
import com.nuguollim.data.repository.template.TemplateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val templateRepository: TemplateRepository
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

    }

    fun saveTemplate(writing: Writing) {
        viewModelScope.launch(exceptionHandler) {
            templateRepository.saveTemplate(writing)
        }
    }
}