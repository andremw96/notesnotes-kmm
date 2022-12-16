//
//  LoginViewModel.swift
//  iosApp
//
//  Created by Quipper Indonesia on 16/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

extension LoginScreen {
    @MainActor class ViewModel: ObservableObject {
        @Published var viewState: LoginViewState = LoginViewState()
        
        private let loginDataValidator: LoginDataValidator
        
        init(loginDataValidator: LoginDataValidator) {
            self.loginDataValidator = loginDataValidator
        }
        
        func validateUsernamePassword(username: String, password: String) {
            viewState = LoginViewState(
                username: username,
                password: password,
                usernameError: (loginDataValidator.checkUsername(username: username) as? LoginDataValidator.LoginValidatorResultError)?.message ?? "",
                passwordError: (loginDataValidator.checkPassword(password: password) as? LoginDataValidator.LoginValidatorResultError)?.message ?? ""
            )
        }
        
        func login() -> Void {
            
        }
    }
}
