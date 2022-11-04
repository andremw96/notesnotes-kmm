package com.andremw96.notesnotes_kmm.model.repository

import com.andremw96.notesnotes_kmm.model.Credential
import com.andremw96.notesnotes_kmm.network.model.login.LoginUserResponse
import com.andremw96.notesnotes_kmm.network.utils.Resource

interface LoginRepository {
    suspend fun login(username: String, password: String): Resource<LoginUserResponse>
    fun saveCredential(token: String, username: String, userId: Int)
    fun getCredential(): Credential
    fun getAccessToken(): String?
}
