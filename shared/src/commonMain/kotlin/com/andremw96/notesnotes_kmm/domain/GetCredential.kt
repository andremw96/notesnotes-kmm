package com.andremw96.notesnotes_kmm.domain

import com.andremw96.notesnotes_kmm.model.Credential

interface GetCredential {
    suspend operator fun invoke(): Credential
}
