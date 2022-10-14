package com.andremw96.notesnotes_kmm.android.domain

interface Login {
    fun invoke(email: String, password: String): String
}
