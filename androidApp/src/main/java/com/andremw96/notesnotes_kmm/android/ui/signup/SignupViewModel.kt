package com.andremw96.notesnotes_kmm.android.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andremw96.notesnotes_kmm.domain.Login
import com.andremw96.notesnotes_kmm.domain.SaveCredential
import com.andremw96.notesnotes_kmm.domain.Signup
import com.andremw96.notesnotes_kmm.model.repository.SignupDataValidator
import com.andremw96.notesnotes_kmm.network.utils.Resource
import kotlinx.coroutines.launch

class SignupViewModel(
    private val signupDataValidator: SignupDataValidator,
    private val signupUseCase: Signup,
    private val loginUseCase: Login,
    private val saveCredential: SaveCredential,
) : ViewModel() {
    private var _signupState: MutableLiveData<SignupState> = MutableLiveData()
    val signupState: LiveData<SignupState> = _signupState

    fun signUpDataChanged(
        email: String,
        username: String,
        password: String,
        confirmationPassword: String,
    ) {
        var emailError: String? = null
        val checkEmail = signupDataValidator.checkEmail(email)
        if (checkEmail is SignupDataValidator.SignupValidatorResult.Error) {
            emailError = checkEmail.message
        }

        var usernameError: String? = null
        val checkUsername = signupDataValidator.checkUsername(username)
        if (checkUsername is SignupDataValidator.SignupValidatorResult.Error) {
            usernameError = checkUsername.message
        }

        var passwordError: String? = null
        val checkPassword = signupDataValidator.checkPassword(password)
        if (checkPassword is SignupDataValidator.SignupValidatorResult.Error) {
            passwordError = checkPassword.message
        }

        var confirmationPasswordError: String? = null
        val checkConfirmationPassword =
            signupDataValidator.checkConfirmationPassword(password, confirmationPassword)
        if (checkConfirmationPassword is SignupDataValidator.SignupValidatorResult.Error) {
            confirmationPasswordError = checkConfirmationPassword.message
        }

        _signupState.postValue(
            SignupState(
                email = email,
                username = username,
                password = password,
                confirmationPassword = confirmationPassword,
                usernameError = usernameError,
                passwordError = passwordError,
                emailError = emailError,
                confirmationPasswordError = confirmationPasswordError
            )
        )
    }

    fun signup(email: String, username: String, password: String) {
        viewModelScope.launch {
            _signupState.postValue(
                SignupState(
                    email = email,
                    username = username,
                    password = password,
                    isLoading = true,
                    isLoginSuccess = false,
                )
            )

            if (_signupState.value?.isDataValid == true) {
                val signup = signupUseCase.invoke(
                    email = email,
                    username = username,
                    password = password
                )
                if (signup is Resource.Success) {
                    _signupState.postValue(
                        SignupState(
                            email = email,
                            username = username,
                            password = password,
                            confirmationPassword = password,
                            emailError = null,
                            usernameError = null,
                            passwordError = null,
                            confirmationPasswordError = null,
                            isLoading = false,
                            buttonError = null
                        )
                    )

                    _signupState.postValue(
                        SignupState(
                            email = email,
                            username = username,
                            password = password,
                            isLoading = true,
                            isLoginSuccess = false,
                        )
                    )

                    val login = loginUseCase.invoke(
                        username, password
                    )
                    if (login is Resource.Success) {
                        _signupState.postValue(
                            SignupState(
                                email = email,
                                username = username,
                                password = password,
                                confirmationPassword = password,
                                emailError = null,
                                usernameError = null,
                                passwordError = null,
                                confirmationPasswordError = null,
                                isLoading = false,
                                isLoginSuccess = true,
                                buttonError = null
                            )
                        )
                        login.data?.accessToken?.let { accessToken ->
                            login.data?.user?.userId?.let { userId ->
                                saveCredential.invoke(username, accessToken, userId)
                            }
                        }
                    } else {
                        _signupState.postValue(
                            SignupState(
                                email = email,
                                username = username,
                                password = password,
                                confirmationPassword = password,
                                emailError = null,
                                usernameError = null,
                                passwordError = null,
                                confirmationPasswordError = null,
                                isLoading = false,
                                isLoginSuccess = false,
                                buttonError = login.message
                            )
                        )
                    }
                } else {
                    _signupState.postValue(
                        SignupState(
                            email = email,
                            username = username,
                            password = password,
                            confirmationPassword = password,
                            emailError = null,
                            usernameError = null,
                            passwordError = null,
                            confirmationPasswordError = null,
                            isLoading = false,
                            isLoginSuccess = false,
                            buttonError = signup.message
                        )
                    )
                }
            }
        }
    }
}
