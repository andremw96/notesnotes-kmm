package com.andremw96.notesnotes_kmm.domain.impl

import com.andremw96.notesnotes_kmm.domain.SaveCredential
import com.andremw96.notesnotes_kmm.model.repository.LoginRepository

class SaveCredentialImpl(
    private val loginRepository: LoginRepository
) : SaveCredential {
    override suspend fun invoke(username: String, token: String) {
        loginRepository.saveCredential(token = token, username = username)
    }
}
