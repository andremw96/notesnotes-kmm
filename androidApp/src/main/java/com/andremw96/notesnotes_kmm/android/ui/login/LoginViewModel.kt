package com.andremw96.notesnotes_kmm.android.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andremw96.notesnotes_kmm.domain.Login
import com.andremw96.notesnotes_kmm.model.LoginDataValidator

class LoginViewModel(
    private val login: Login,
    private val loginDataValidator: LoginDataValidator,
) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    fun loginDataChanged(email: String, password: String) {
        var emailError: String? = null
        val checkEmail = loginDataValidator.checkEmail(email)
        if (checkEmail is LoginDataValidator.LoginValidatorResult.Error) {
            emailError = checkEmail.message
        }

        var pwdError: String? = null
        val checkPwd = loginDataValidator.checkPassword(password)
        if (checkPwd is LoginDataValidator.LoginValidatorResult.Error) {
            pwdError = checkPwd.message
        }

        _loginForm.value = LoginFormState(
            email = email,
            password = password,
            emailError = emailError,
            passwordError = pwdError,
        )
    }

    suspend fun login(email: String, password: String): String {
        return if (_loginForm.value?.isDataValid == true) login.invoke(email, password) else ""
    }
}
