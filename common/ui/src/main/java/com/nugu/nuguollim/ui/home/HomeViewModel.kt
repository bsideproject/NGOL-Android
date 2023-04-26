package com.nugu.nuguollim.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nugu.nuguollim.common.data.model.template.AllTemplate
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.common.data.model.template.TemplateSort
import com.nuguollim.data.repository.template.TemplateRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val templateRepository: TemplateRepositoryImpl
) : ViewModel() {

    private val _templatePaging = MutableStateFlow<PagingData<Template>>(PagingData.empty())
    val templatePaging: StateFlow<PagingData<Template>> = _templatePaging

    private var keyword = ""
    private var sort = TemplateSort.values().first().sortText

    fun setKeyword(keyword: String) {
        this.keyword = keyword

        refreshTemplatePaging()
    }

    fun setSort(sort: String) {
        this.sort = sort

        refreshTemplatePaging()
    }

    fun refreshTemplatePaging() {
        viewModelScope.launch {
            val onResponse: suspend (Int) -> AllTemplate = { page ->
                templateRepository.getTemplates(page, sort, keyword)
            }

            val pagingSource = TemplatePagingSource(onResponse)
            val pagingConfig = PagingConfig(pageSize = 20)
            val pager = Pager(
                config = pagingConfig,
                pagingSourceFactory = { pagingSource }
            ).flow.cachedIn(viewModelScope)

            _templatePaging.emitAll(pager)
        }
    }
}