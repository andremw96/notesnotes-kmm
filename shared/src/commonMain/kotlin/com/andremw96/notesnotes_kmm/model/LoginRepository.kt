package com.andremw96.notesnotes_kmm.model

interface LoginRepository {
    fun login(username: String, password: String): String
}
