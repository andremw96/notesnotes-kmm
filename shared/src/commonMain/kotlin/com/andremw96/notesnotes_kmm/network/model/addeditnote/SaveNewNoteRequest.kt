package com.andremw96.notesnotes_kmm.network.model.addeditnote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SaveNewNoteRequest(
    @SerialName("user_id") val userId: Int,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String?,
)
