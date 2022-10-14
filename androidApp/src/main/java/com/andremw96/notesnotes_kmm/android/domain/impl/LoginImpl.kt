package com.andremw96.notesnotes_kmm.android.domain.impl

import com.andremw96.notesnotes_kmm.android.domain.Login
import com.andremw96.notesnotes_kmm.android.model.repository.LoginRepository
import javax.inject.Inject

class LoginImpl @Inject constructor(
    private val loginRepository: LoginRepository,
) : Login {
    override fun invoke(email: String, password: String): String {
        return loginRepository.login(email, password)
    }
}
