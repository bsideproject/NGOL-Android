package com.nugu.nuguollim.ui.search.template

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.nugu.nuguollim.di.IoDispatcher
import com.nugu.paging.template.TemplatePagingSource
import com.nugu.search.nav.SearchParameterData
import com.nuguollim.data.state.resultStateFlow
import com.nuguollim.data.usecase.template.AddFavoriteUseCase
import com.nuguollim.data.usecase.template.GetTemplatesUseCase
import com.nuguollim.data.usecase.template.RemoveFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class TemplateSearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val templatePagingSourceFactory: TemplatePagingSource.Factory,
    private val getTemplatesUseCase: GetTemplatesUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val searchArgs get() = SearchArgs(savedStateHandle)

    private val searchParamsData get() = searchArgs.data
    val keyword get() = searchParamsData?.keyword ?: ""

    private val defaultParams get() = GetTemplatesUseCase.Params(
        targetId = searchParamsData?.targetId,
        themeId = searchParamsData?.themeId,
        keyword = searchParamsData?.keyword,
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

    fun addFavorite(id: Long) {
        viewModelScope.launch { addFavoriteUseCase.run(id) }
    }

    fun removeFavorite(id: Long) {
        viewModelScope.launch { removeFavoriteUseCase.run(id) }
    }

}

private class SearchArgs(stateSavedStateHandle: SavedStateHandle) {
    val data = stateSavedStateHandle.get<SearchParameterData>("data")
}