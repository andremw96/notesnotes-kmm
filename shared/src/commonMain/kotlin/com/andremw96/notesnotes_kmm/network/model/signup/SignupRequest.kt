package com.andremw96.notesnotes_kmm.network.model.signup

import kotlinx.serialization.Serializable

@Serializable
data class SignupRequest(
    val email: String,
    val username: String,
    val password: String,
)
