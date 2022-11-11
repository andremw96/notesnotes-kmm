package com.andremw96.notesnotes_kmm.network.model.login.response

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class LoginUserResponse(
    @SerialName("access_token") val accessToken: String,
    @SerialName("user") val user: UserResponse
)

@kotlinx.serialization.Serializable
data class UserResponse(
    @SerialName("created_at") val createdAt: String,
    @SerialName("email") val email: String,
    @SerialName("first_name") val firstName: Name,
    @SerialName("full_name") val fullName: Name,
    @SerialName("last_name") val lastName: Name,
    @SerialName("notes_count") val notesCount: Int,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("user_id") val userId: Int,
    @SerialName("username") val username: String
)

@kotlinx.serialization.Serializable
data class Name(
    @SerialName("String") val string: String,
    @SerialName("Valid") val valid: Boolean
)

