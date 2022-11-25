package com.andremw96.notesnotes_kmm.model.repository

class LoginDataValidator {
    sealed class LoginValidatorResult {
        object Success : LoginValidatorResult()
        class Error(val message: String) : LoginValidatorResult()
    }

    fun checkUsername(username: String): LoginValidatorResult {
        return if (isUsernameValid(username)) LoginValidatorResult.Success else LoginValidatorResult.Error(
            "Username is not valid"
        )
    }

    fun checkPassword(password: String): LoginValidatorResult {
        return when {
            password.length < 5 -> LoginValidatorResult.Error("Password must be >5 characters")
            password.lowercase() == "password" -> LoginValidatorResult.Error("Password shouldn't be \"password\"")
            else -> LoginValidatorResult.Success
        }
    }

    private fun isUsernameValid(username: String) = username.isNotBlank() && username.isNotEmpty()
}
