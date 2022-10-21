package com.andremw96.notesnotes_kmm.network.model

data class LoginResponse(
    val accessToken: String,
    val user: LoginUserResponse,
)

data class LoginUserResponse(
    val userId: String,
    val fullName: String,
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val notesCount: Int,
)
