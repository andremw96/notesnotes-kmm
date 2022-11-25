package com.andremw96.notesnotes_kmm.model.repository

class SignupDataValidator {
    private val emailRegex =
        ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+").toRegex()

    sealed class SignupValidatorResult {
        object Success : SignupValidatorResult()
        class Error(val message: String) : SignupValidatorResult()
    }

    private fun isEmailValid(email: String) =
        email.isNotBlank() && email.isNotEmpty() && emailRegex.matches(email)

    fun checkEmail(email: String): SignupValidatorResult {
        return when {
            email.length < 5 -> SignupValidatorResult.Error("Email must be >5 characters")
            email.lowercase() == "email" -> SignupValidatorResult.Error("Password shouldn't be \"email\"")
            !isEmailValid(email) -> SignupValidatorResult.Error("email is not a valid email")
            else -> SignupValidatorResult.Success
        }
    }

    private fun isUsernameValid(username: String) = username.isNotBlank() && username.isNotEmpty()

    fun checkUsername(username: String): SignupValidatorResult {
        return if (isUsernameValid(username)) SignupValidatorResult.Success else SignupValidatorResult.Error(
            "Username is not valid"
        )
    }

    fun checkPassword(password: String): SignupValidatorResult {
        return when {
            password.length < 6 -> SignupValidatorResult.Error("Password must be >6 characters")
            password.lowercase() == "password" -> SignupValidatorResult.Error("Password shouldn't be \"password\"")
            else -> SignupValidatorResult.Success
        }
    }

    fun checkConfirmationPassword(
        password: String,
        confirmationPassword: String
    ): SignupValidatorResult {
        return if (password == confirmationPassword) SignupValidatorResult.Success else SignupValidatorResult.Error(
            "Confirmation password is different with passwordø"
        )
    }
}
