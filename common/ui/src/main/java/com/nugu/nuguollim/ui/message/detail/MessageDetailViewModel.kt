package com.nugu.nuguollim.ui.message.detail

import androidx.lifecycle.ViewModel
import com.nuguollim.data.repository.template.TemplateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MessageDetailViewModel @Inject constructor(
    private val templateRepository: TemplateRepository
) : ViewModel() {
}