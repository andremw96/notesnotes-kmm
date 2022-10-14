package com.andremw96.notesnotes_kmm.android.ui.login

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null
) {
    val isDataValid: Boolean
        get() = emailError == null && passwordError == null
}
