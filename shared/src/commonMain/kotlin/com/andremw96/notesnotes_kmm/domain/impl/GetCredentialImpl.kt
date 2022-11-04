package com.andremw96.notesnotes_kmm.domain.impl

import com.andremw96.notesnotes_kmm.domain.GetCredential
import com.andremw96.notesnotes_kmm.model.Credential
import com.andremw96.notesnotes_kmm.model.repository.CredentialRepository

class GetCredentialImpl(
    private val credentialRepository: CredentialRepository,
) : GetCredential {
    override suspend operator fun invoke(): Credential {
        val credential = credentialRepository.getCredential()
        if (credential.accessToken != null && credential.userid != null && credential.username != null) {
            return Credential(credential.accessToken, credential.username, credential.userid)
        }
        return Credential("", "", -1)
    }
}
