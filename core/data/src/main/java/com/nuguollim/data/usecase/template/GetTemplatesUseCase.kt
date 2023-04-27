package com.nuguollim.data.usecase.template

import com.nugu.nuguollim.common.data.model.template.AllTemplate
import com.nuguollim.data.repository.template.TemplateRepository
import javax.inject.Inject

class GetTemplatesUseCase @Inject constructor(
    private val templateRepository: TemplateRepository
) {

    suspend fun run(params: Params) = invoke(params)

    private suspend operator fun invoke(params: Params): AllTemplate =
        templateRepository.getTemplates(
            targetId = params.targetId,
            themeId = params.themeId,
            keyword = params.keyword,
            page = params.page,
            sort = params.sort,
        )

    data class Params(
        val keyword: String? = null,
        val targetId: Long? = null,
        val themeId: Long? = null,
        val page: Int = 0,
        val sort: String
    )

}