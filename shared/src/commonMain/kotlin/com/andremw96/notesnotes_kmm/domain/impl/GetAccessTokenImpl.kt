package com.andremw96.notesnotes_kmm.domain.impl

import com.andremw96.notesnotes_kmm.domain.GetAccessToken
import com.andremw96.notesnotes_kmm.model.repository.CredentialRepository

class GetAccessTokenImpl(
    private val credentialRepository: CredentialRepository,
) : GetAccessToken {
    override operator fun invoke(): String? {
        return credentialRepository.getAccessToken()
    }
}
