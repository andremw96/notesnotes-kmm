package com.andremw96.notesnotes_kmm.domain.impl

import com.andremw96.notesnotes_kmm.domain.Login
import com.andremw96.notesnotes_kmm.model.LoginRepository

class LoginImpl constructor(
    private val loginRepository: LoginRepository,
) : Login {
    override fun invoke(email: String, password: String): String {
        return loginRepository.login(email, password)
    }
}
