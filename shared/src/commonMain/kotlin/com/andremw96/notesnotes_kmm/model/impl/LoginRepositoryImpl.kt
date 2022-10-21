package com.andremw96.notesnotes_kmm.model.impl

import com.andremw96.notesnotes_kmm.model.LoginRepository

class LoginRepositoryImpl : LoginRepository {
    override fun login(username: String, password: String): String {
        return "login success $username $password"
    }
}
