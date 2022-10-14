package com.andremw96.notesnotes_kmm.android.model.repository.impl

import com.andremw96.notesnotes_kmm.android.model.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor() : LoginRepository {
    override fun login(username: String, password: String): String {
        return "login success $username $password"
    }
}
