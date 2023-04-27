package com.nuguollim.data.usecase.template

import com.nugu.nuguollim.common.data.model.template.FavoriteData
import com.nuguollim.data.repository.template.TemplateRepository
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    private val templateRepository: TemplateRepository
) {

    private suspend operator fun invoke(id: Long): FavoriteData =
        templateRepository.removeFavorite(id)

    suspend fun run(id: Long) = invoke(id)

}