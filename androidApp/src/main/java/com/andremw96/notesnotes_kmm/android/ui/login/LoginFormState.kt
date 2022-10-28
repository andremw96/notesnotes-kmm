package com.andremw96.notesnotes_kmm.android.ui.login

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    val username: String = "",
    val password: String = "",
    val usernameError: String? = null,
    val passwordError: String? = null,
    val email: String? = null,
    val accessToken: String? = null,
    val loginError: String? = null,
) {
    val isDataValid: Boolean
        get() = usernameError == null && passwordError == null
}
