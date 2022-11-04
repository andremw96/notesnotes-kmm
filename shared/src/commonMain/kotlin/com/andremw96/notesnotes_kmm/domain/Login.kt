package com.andremw96.notesnotes_kmm.domain

import com.andremw96.notesnotes_kmm.network.model.login.LoginUserResponse
import com.andremw96.notesnotes_kmm.network.utils.Resource

interface Login {
    suspend fun invoke(username: String, password: String): Resource<LoginUserResponse>
}
