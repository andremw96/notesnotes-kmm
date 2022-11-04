package com.andremw96.notesnotes_kmm.domain.impl

import com.andremw96.notesnotes_kmm.domain.GetCredential
import com.andremw96.notesnotes_kmm.model.Credential
import com.andremw96.notesnotes_kmm.model.repository.LoginRepository

class GetCredentialImpl(
    private val loginRepository: LoginRepository,
) : GetCredential {
    override suspend operator fun invoke(): Credential {
        val credential = loginRepository.getCredential()
        if (credential.accessToken != null && credential.userid != null && credential.username != null) {
            return Credential(credential.accessToken, credential.username, credential.userid)
        }
        return Credential("", "", -1)
    }
}
