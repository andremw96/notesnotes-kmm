package com.andremw96.notesnotes_kmm.domain.impl

import com.andremw96.notesnotes_kmm.domain.SaveCredential
import com.andremw96.notesnotes_kmm.model.repository.CredentialRepository

class SaveCredentialImpl(
    private val credentialRepository: CredentialRepository
) : SaveCredential {
    override suspend fun invoke(username: String, token: String, userId: Int) {
        credentialRepository.saveCredential(token = token, username = username, userId = userId)
    }
}
