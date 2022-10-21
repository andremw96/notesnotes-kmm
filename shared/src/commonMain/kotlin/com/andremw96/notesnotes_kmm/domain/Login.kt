package com.andremw96.notesnotes_kmm.domain

interface Login {
    suspend fun invoke(email: String, password: String): String
}
