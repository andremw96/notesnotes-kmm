package com.andremw96.notesnotes_kmm.network.model.addeditnote

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SaveNewNoteResponse(
    @SerialName("note") val note: NoteResponse,
    @SerialName("user") val user: UserResponse
)

@kotlinx.serialization.Serializable
data class NoteResponse(
    @SerialName("created_at") val createdAt: String,
    @SerialName("description") val description: Description,
    @SerialName("id") val id: Int,
    @SerialName("is_deleted") val isDeleted: Boolean,
    @SerialName("title") val title: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("user_id") val userId: Int
)

@kotlinx.serialization.Serializable
data class UserResponse(
    @SerialName("created_at") val createdAt: String,
    @SerialName("email") val email: String,
    @SerialName("first_name") val firstName: Description,
    @SerialName("full_name") val fullName: Description,
    @SerialName("last_name") val lastName: Description,
    @SerialName("notes_count") val notesCount: Int,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("username") val username: String
)

@kotlinx.serialization.Serializable
data class Description(
    @SerialName("String") val string: String,
    @SerialName("Valid") val valid: Boolean
)
