package com.nugu.nuguollim.ui.search.theme

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.nugu.nuguollim.di.IoDispatcher
import com.nugu.paging.template.TemplatePagingSource
import com.nugu.nuguollim.common.data.model.search.target.TemplateTargetData
import com.nuguollim.data.state.resultStateFlow
import com.nuguollim.data.usecase.search.GetTemplatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class ThemeSearchViewModel @Inject constructor(
    stateSavedStateHandle: SavedStateHandle,
    private val templatePagingSourceFactory: TemplatePagingSource.Factory,
    private val getTemplatesUseCase: GetTemplatesUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val targetArgs = TargetArgs(stateSavedStateHandle)
    val targetData: TemplateTargetData get() = targetArgs.targetData

    private val defaultParams = GetTemplatesUseCase.Params(
        targetId = targetData.id.toLong(),
        sort = "viewCount,desc"
    )

    private val _templatesParams = MutableStateFlow(defaultParams)
    val templatesParams = _templatesParams.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    ).filterNotNull().debounce(1_000).mapLatest { params ->
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                templatePagingSourceFactory.create(
                    useCaseParams = params,
                    getTemplatesUseCase = getTemplatesUseCase
                )
            }
        ).flow.cachedIn(viewModelScope.plus(ioDispatcher))
    }.resultStateFlow

    fun updateSort(sort: String) {
        _templatesParams.update { it.copy(sort = sort) }
    }

    fun updateTheme(themeId: Int?) {
        _templatesParams.update { it.copy(themeId = themeId?.toLong()) }
    }

}

private class TargetArgs(stateSavedStateHandle: SavedStateHandle) {
    val targetData = stateSavedStateHandle.get<TemplateTargetData>("data")!!
}