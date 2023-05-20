package com.nugu.paging.template

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nugu.nuguollim.common.data.model.template.MyWritingTemplateData
import com.nuguollim.data.usecase.template.GetMyWritingTemplatesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class MyWritingTemplatePagingSource @AssistedInject internal constructor(
    @Assisted private val getMyWritingTemplatesUseCase: GetMyWritingTemplatesUseCase
) : PagingSource<Int, MyWritingTemplateData>() {

    private companion object {
        private const val PAGING_INDEX = 0
    }

    override fun getRefreshKey(state: PagingState<Int, MyWritingTemplateData>): Int? =
        state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyWritingTemplateData> {
        val position = params.key ?: PAGING_INDEX

        return try {
            val loadData = getMyWritingTemplatesUseCase.run(position)
            LoadResult.Page(
                data = loadData.data.content,
                prevKey = if (position == PAGING_INDEX) null else position - 1,
                nextKey = if (loadData.data.content.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(getMyWritingTemplatesUseCase: GetMyWritingTemplatesUseCase): MyWritingTemplatePagingSource
    }

}