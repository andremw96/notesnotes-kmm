package com.andremw96.notesnotes_kmm.android.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andremw96.notesnotes_kmm.domain.Login
import com.andremw96.notesnotes_kmm.model.repository.LoginDataValidator
import com.andremw96.notesnotes_kmm.network.utils.Resource

class LoginViewModel(
    private val login: Login,
    private val loginDataValidator: LoginDataValidator,
) : ViewModel() {

    private val _loginForm = MutableLiveData<Resource<LoginFormState>>()
    val loginFormState: LiveData<Resource<LoginFormState>> = _loginForm

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
            Resource.SuccessDoNothing(
                LoginFormState(
                    username = username,
                    password = password,
                    usernameError = usernameError,
                    passwordError = pwdError,
                )
            )
        )
    }

    suspend fun login(username: String, password: String) {
        _loginForm.postValue(Resource.Loading())

        if (_loginForm.value?.data?.isDataValid == true) {
            val login = login.invoke(username, password)
            if (login is Resource.Success) {
                _loginForm.postValue(
                    Resource.Success(
                        LoginFormState(
                            username = username,
                            password = password,
                            email = login.data?.user?.email,
                            accessToken = login.data?.accessToken,
                            usernameError = null,
                            passwordError = null,
                            loginError = null
                        )
                    )
                )
            } else {
                _loginForm.postValue(
                    Resource.Error(login.message ?: "Error")
                )
            }
        }
    }

    fun resetState() {
        _loginForm.postValue(
            Resource.Idle()
        )
    }
}
