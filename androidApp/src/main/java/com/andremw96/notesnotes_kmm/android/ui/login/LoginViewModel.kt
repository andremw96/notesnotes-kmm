package com.andremw96.notesnotes_kmm.android.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andremw96.notesnotes_kmm.domain.Login
import com.andremw96.notesnotes_kmm.model.repository.LoginDataValidator
import com.andremw96.notesnotes_kmm.network.utils.Resource
import kotlinx.coroutines.launch

class LoginViewModel(
    private val login: Login,
    private val loginDataValidator: LoginDataValidator,
) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    fun loginDataChanged(username: String, password: String) {
        var usernameError: String? = null
        val checkUsername = loginDataValidator.checkUsername(username)
        if (checkUsername is LoginDataValidator.LoginValidatorResult.Error) {
            usernameError = checkUsername.message
        }

        var pwdError: String? = null
        val checkPwd = loginDataValidator.checkPassword(password)
        if (checkPwd is LoginDataValidator.LoginValidatorResult.Error) {
            pwdError = checkPwd.message
        }

        _loginForm.postValue(
            LoginFormState(
                username = username,
                password = password,
                usernameError = usernameError,
                passwordError = pwdError,
            )
        )
    }

    suspend fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginForm.postValue(
                LoginFormState(
                    username = username,
                    password = password,
                    isLoading = true,
                    isLoginSuccess = false
                )
            )

            if (_loginForm.value?.isDataValid == true) {
                val login = login.invoke(username, password)
                if (login is Resource.Success) {
                    _loginForm.postValue(
                        LoginFormState(
                            username = username,
                            password = password,
                            email = login.data?.user?.email,
                            accessToken = login.data?.accessToken,
                            usernameError = null,
                            passwordError = null,
                            loginError = null,
                            isLoginSuccess = true
                        )
                    )
                } else {
                    _loginForm.postValue(
                        LoginFormState(
                            username = username,
                            password = password,
                            email = null,
                            accessToken = null,
                            usernameError = null,
                            passwordError = null,
                            loginError = login.message,
                            isLoginSuccess = false
                        )
                    )
                }
            }
        }
    }
}
