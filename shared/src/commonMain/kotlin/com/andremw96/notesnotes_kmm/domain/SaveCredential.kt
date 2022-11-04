package com.andremw96.notesnotes_kmm.domain

interface SaveCredential {
    suspend fun invoke(username: String, token: String, userId: Int)
}
