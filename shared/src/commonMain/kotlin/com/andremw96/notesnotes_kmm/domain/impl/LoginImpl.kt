package com.andremw96.notesnotes_kmm.domain.impl

import com.andremw96.notesnotes_kmm.domain.Login
import com.andremw96.notesnotes_kmm.model.repository.LoginRepository
import com.andremw96.notesnotes_kmm.network.model.login.response.LoginUserResponse
import com.andremw96.notesnotes_kmm.network.utils.Resource

class LoginImpl constructor(
    private val loginRepository: LoginRepository,
) : Login {
    override suspend fun invoke(username: String, password: String): Resource<LoginUserResponse> {
        return loginRepository.login(username, password)
    }
}
