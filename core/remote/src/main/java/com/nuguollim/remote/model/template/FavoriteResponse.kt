package com.nuguollim.remote.model.template

import com.nugu.nuguollim.common.data.model.template.FavoriteData

data class FavoriteResponse(val message: String) {
    companion object {
        fun FavoriteResponse.asExternalModel(): FavoriteData = FavoriteData(message)
    }
}