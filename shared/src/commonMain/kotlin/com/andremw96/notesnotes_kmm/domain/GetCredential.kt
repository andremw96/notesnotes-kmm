package com.andremw96.notesnotes_kmm.domain

interface GetCredential {
    suspend fun invoke(): Pair<String, String>
}
