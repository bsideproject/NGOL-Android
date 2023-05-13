package com.nugu.nuguollim.ui.message.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nuguollim.data.repository.paper.PaperRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MessageEditViewModel @Inject constructor(
    private val paperRepositoryImpl: PaperRepositoryImpl
) : ViewModel() {

    val papers = paperRepositoryImpl.getPapers().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )
}