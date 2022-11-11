package com.andremw96.notesnotes_kmm.model.repository

import com.andremw96.notesnotes_kmm.model.Credential

interface CredentialRepository {
    fun saveCredential(token: String, username: String, userId: Int)
    fun getCredential(): Credential
    fun getAccessToken(): String?
    fun removeCredential()
}
