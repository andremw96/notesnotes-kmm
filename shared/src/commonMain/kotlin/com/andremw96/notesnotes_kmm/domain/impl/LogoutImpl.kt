package com.andremw96.notesnotes_kmm.domain.impl

import com.andremw96.notesnotes_kmm.domain.Logout
import com.andremw96.notesnotes_kmm.model.repository.CredentialRepository

class LogoutImpl(
    private val credentialRepository: CredentialRepository
) : Logout {
    override suspend operator fun invoke() {
        credentialRepository.removeCredential()
    }
}
