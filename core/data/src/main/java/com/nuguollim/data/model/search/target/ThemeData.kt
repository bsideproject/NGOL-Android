package com.nuguollim.data.model.search.target

import com.nuguollim.remote.model.search.target.ThemeResponse
import java.io.Serializable

data class ThemeData(
    val id: Int,
    val name: String
) : Serializable {
    companion object {
        fun ThemeResponse.asExternalModel(): ThemeData = ThemeData(
            id = id,
            name = name
        )
    }
}