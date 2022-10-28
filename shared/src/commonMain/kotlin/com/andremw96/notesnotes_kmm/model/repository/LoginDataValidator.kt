package com.andremw96.notesnotes_kmm.model.repository

class LoginDataValidator {

    companion object {
        private val emailRegex =
            ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+").toRegex()
    }

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
