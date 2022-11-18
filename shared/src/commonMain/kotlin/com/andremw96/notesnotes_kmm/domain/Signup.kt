package com.andremw96.notesnotes_kmm.domain

import com.andremw96.notesnotes_kmm.domain.model.SignupSchema
import com.andremw96.notesnotes_kmm.network.utils.Resource

interface Signup {
    suspend fun invoke(email: String, username: String, password: String): Resource<SignupSchema>
}
