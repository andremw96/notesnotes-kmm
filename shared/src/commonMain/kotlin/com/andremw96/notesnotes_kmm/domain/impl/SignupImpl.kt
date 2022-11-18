package com.andremw96.notesnotes_kmm.domain.impl

import com.andremw96.notesnotes_kmm.domain.Signup
import com.andremw96.notesnotes_kmm.domain.model.SignupSchema
import com.andremw96.notesnotes_kmm.model.repository.LoginRepository
import com.andremw96.notesnotes_kmm.network.utils.Resource

class SignupImpl(
    private val loginRepository: LoginRepository,
) : Signup {
    override suspend fun invoke(
        email: String,
        username: String,
        password: String
    ): Resource<SignupSchema> {
        return SignupSchema.responseToSchema(loginRepository.signup(email, username, password))
    }
}
