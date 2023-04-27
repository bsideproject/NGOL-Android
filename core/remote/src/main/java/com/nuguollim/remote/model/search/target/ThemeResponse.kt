package com.nuguollim.remote.model.search.target

import com.nugu.nuguollim.common.data.model.search.target.ThemeData

data class ThemeResponse(
    val id: Int,
    val name: String
)  {
    companion object {
        fun ThemeResponse.asExternalModel(): ThemeData = ThemeData(
            id = id,
            name = name
        )
    }
}
