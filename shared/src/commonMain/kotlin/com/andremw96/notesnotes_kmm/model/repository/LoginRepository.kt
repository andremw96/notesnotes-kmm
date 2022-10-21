package com.andremw96.notesnotes_kmm.model.repository

interface LoginRepository {
    suspend fun login(username: String, password: String): String
}
