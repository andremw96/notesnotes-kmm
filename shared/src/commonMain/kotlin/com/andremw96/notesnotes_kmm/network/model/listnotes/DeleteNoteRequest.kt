package com.andremw96.notesnotes_kmm.network.model.listnotes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteNoteRequest(
    @SerialName("user_id") val userId: Int,
    @SerialName("note_id") val noteId: Int,
)
