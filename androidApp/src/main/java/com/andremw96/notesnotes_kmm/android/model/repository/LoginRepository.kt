package com.andremw96.notesnotes_kmm.android.model.repository

interface LoginRepository {
    fun login(username: String, password: String): String
}
