package com.andremw96.notesnotes_kmm.domain

import com.andremw96.notesnotes_kmm.network.model.LoginUserResponse
import com.andremw96.notesnotes_kmm.network.utils.Resource

interface SaveCredential {
    suspend fun invoke(username: String, token: String)
}
