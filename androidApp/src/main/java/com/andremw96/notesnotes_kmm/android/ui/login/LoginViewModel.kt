package com.andremw96.notesnotes_kmm.android.ui.login

import androidx.lifecycle.ViewModel
import com.andremw96.notesnotes_kmm.android.domain.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: Login,
) : ViewModel() {
    fun login(email: String, password: String): String {
        return login.invoke(email, password)
    }
}
