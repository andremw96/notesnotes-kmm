package com.andremw96.notesnotes_kmm.network.model.login.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val username: String,
    val password: String,
)
