package com.andremw96.notesnotes_kmm.domain.impl

import com.andremw96.notesnotes_kmm.domain.GetCredential
import com.andremw96.notesnotes_kmm.model.repository.LoginRepository

class GetCredentialImpl(
    private val loginRepository: LoginRepository,
) : GetCredential {
    override suspend fun invoke(): Pair<String, String> {
        val credential = loginRepository.getCredential()
        if (credential.first != null && credential.second != null) {
            return Pair(credential.first!!, credential.second!!)
        }
        return Pair("", "")
    }
}
