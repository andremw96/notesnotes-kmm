//
//  LoginViewState.swift
//  iosApp
//
//  Created by Quipper Indonesia on 16/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation

struct LoginViewState {
    var username = ""
    var password = ""
    var usernameError: String? = "please fill username"
    var passwordError: String? = "please fill password"
    var isLoading = false
    var accessToken = ""
    var loginError = ""
    var isLoginSuccess = false
    
    var isDataValid: Bool {
        get { return usernameError == nil && usernameError == "" && passwordError == nil && passwordError == "" }
    }
    
    var isLoginError: Bool {
        get { return loginError != "" }
    }
}
