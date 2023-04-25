package com.nugu.nuguollim.ui.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nugu.nuguollim.common.data.model.template.AllTemplate
import com.nugu.nuguollim.common.data.model.template.Template

class TemplatePagingSource(
    private val onTemplateResponse: suspend (Int) -> AllTemplate
) : PagingSource<Int, Template>() {

    override fun getRefreshKey(state: PagingState<Int, Template>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val closetPosition = state.closestPageToPosition(anchorPosition)

            closetPosition?.let {
                it.prevKey?.plus(1)
                    ?: it.nextKey?.minus(1)
            }
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Template> {
        return try {
            val page = params.key ?: 0
            val response = onTemplateResponse(page)

            LoadResult.Page(
                data = response.data.content,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.data.last) null else page.plus(1)
            )
        } catch (e: java.lang.Exception) {
            LoadResult.Error(e)
        }
    }
}