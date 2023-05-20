package com.nuguollim.data.usecase.template

import com.nugu.nuguollim.common.data.model.template.AllMyWritingTemplateData
import com.nuguollim.data.repository.template.TemplateRepository
import javax.inject.Inject

class GetMyWritingTemplatesUseCase @Inject constructor(
    private val templateRepository: TemplateRepository
) {

    suspend fun run(page: Int) = invoke(page)

    private suspend operator fun invoke(page: Int): AllMyWritingTemplateData =
        templateRepository.getMyWritingTemplates(page)

}