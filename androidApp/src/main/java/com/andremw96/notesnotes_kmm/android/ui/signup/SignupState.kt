package com.andremw96.notesnotes_kmm.android.ui.signup

/**
 * Data validation state of the login form.
 */
data class SignupState(
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val confirmationPassword: String = "",
    val usernameError: String? = null,
    val passwordError: String? = null,
    val emailError: String? = null,
    val confirmationPasswordError: String? = null,
    val isLoading: Boolean = false,
    val accessToken: String? = null,
    val loginError: String? = null,
    val isLoginSuccess: Boolean = false,
) {
    val isDataValid: Boolean
        get() = usernameError == null && passwordError == null && emailError == null && confirmationPasswordError == null
}
