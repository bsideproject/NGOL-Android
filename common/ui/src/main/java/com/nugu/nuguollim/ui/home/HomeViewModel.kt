package com.nugu.nuguollim.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.nugu.nuguollim.common.data.model.template.AllTemplate
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.common.data.model.template.TemplateSort
import com.nuguollim.data.repository.template.TemplateRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val templateRepository: TemplateRepositoryImpl
) : ViewModel() {

    private val _pagingSource = MutableStateFlow(getTemplatePagingSource())
    val templatePaging: Flow<PagingData<Template>> = _pagingSource.flatMapLatest {source ->
        val pagingConfig = PagingConfig(pageSize = 20)
        Pager(
            config = pagingConfig,
            pagingSourceFactory = { source }
        ).flow.cachedIn(viewModelScope)
    }

    private var keyword = ""
    private var sort = TemplateSort.values().first().sortText

    fun setKeyword(keyword: String) {
        this.keyword = keyword

        _pagingSource.value = getTemplatePagingSource()
    }

    fun setSort(sort: String) {
        this.sort = sort

        _pagingSource.value = getTemplatePagingSource()
    }

    private fun getTemplatePagingSource(): PagingSource<Int, Template> {
        val onResponse: suspend (Int) -> AllTemplate = { page ->
            templateRepository.getTemplates(page, sort, keyword)
        }

        return TemplatePagingSource(onResponse)
    }
}