package com.nuguollim.data.usecase.template

import com.nugu.nuguollim.common.data.model.template.AllTemplate
import com.nuguollim.data.repository.template.TemplateRepository
import javax.inject.Inject

class GetFavoriteTemplatesUseCase @Inject constructor(
    private val templateRepository: TemplateRepository
) {

    suspend fun run(page: Int) = invoke(page)

    private suspend operator fun invoke(page: Int): AllTemplate =
        templateRepository.getFavoriteTemplates(page)

}