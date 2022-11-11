package com.andremw96.notesnotes_kmm.network.model.listnotes.response

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ListNoteResponseItem(
    @SerialName("created_at") val createdAt: String,
    @SerialName("description") val description: Description,
    @SerialName("id") val id: Int,
    @SerialName("is_deleted") val isDeleted: Boolean,
    @SerialName("title") val title: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("user_id") val userId: Int
)

@kotlinx.serialization.Serializable
data class Description(
    @SerialName("String") val string: String,
    @SerialName("Valid") val valid: Boolean
)
