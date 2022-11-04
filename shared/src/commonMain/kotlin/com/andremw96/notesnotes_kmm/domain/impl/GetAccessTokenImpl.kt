package com.andremw96.notesnotes_kmm.domain.impl

import com.andremw96.notesnotes_kmm.domain.GetAccessToken
import com.andremw96.notesnotes_kmm.model.repository.LoginRepository

class GetAccessTokenImpl(
    private val loginRepository: LoginRepository,
) : GetAccessToken {
    override operator fun invoke(): String? {
        return loginRepository.getAccessToken()
    }
}
