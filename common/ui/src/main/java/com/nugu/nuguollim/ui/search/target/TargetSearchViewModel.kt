package com.nugu.nuguollim.ui.search.target

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nuguollim.data.state.ResultState
import com.nuguollim.data.usecase.search.GetTemplateTargetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TargetSearchViewModel @Inject constructor(
    getTemplateTargetsUseCase: GetTemplateTargetsUseCase
) : ViewModel() {

    val templateTargetListState = getTemplateTargetsUseCase.run().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ResultState.Loading
    )

}