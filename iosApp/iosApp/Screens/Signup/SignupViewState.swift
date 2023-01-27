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
    var signupError = ""
    var isLoginSuccess = false
    
    var isDataValid: Bool {
        get { return username != "" && usernameError == "" && password != "" && passwordError == "" && email != "" && emailError == "" && confirmationPassword != "" && confirmationPasswordError == "" }
    }
}
