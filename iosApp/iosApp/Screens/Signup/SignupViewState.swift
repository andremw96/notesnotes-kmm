//
//  SignupViewState.swift
//  iosApp
//
//  Created by Quipper Indonesia on 27/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct SignupViewState {
    var email = ""
    var username = ""
    var password = ""
    var confirmationPassword = ""
    var usernameError = ""
    var passwordError = ""
    var emailError = ""
    var confirmationPasswordError = ""
    var isLoading = false
    var accessToken = ""
    var buttonError = ""
    var isLoginSuccess = false
}
