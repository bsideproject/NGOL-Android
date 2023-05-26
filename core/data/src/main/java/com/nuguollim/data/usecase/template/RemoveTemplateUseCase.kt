package com.nuguollim.data.usecase.template

import com.nuguollim.data.repository.template.TemplateRepository
import javax.inject.Inject

class RemoveTemplateUseCase @Inject constructor(
    private val templateRepository: TemplateRepository
) {

    suspend fun run(id: Long) = invoke(id)

    private suspend operator fun invoke(id: Long) = templateRepository.removeTemplate(id)

}