//
//  LoginViewModel.swift
//  iosApp
//
//  Created by Quipper Indonesia on 16/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared


@MainActor class LoginViewModel: ObservableObject {
    @Published var viewState: LoginViewState = LoginViewState()
    
    private let loginDataValidator: LoginDataValidator
    private let loginUseCase: Login
    
    init(loginDataValidator: LoginDataValidator, loginUseCase: Login) {
        self.loginDataValidator = loginDataValidator
        self.loginUseCase = loginUseCase
    }
    
    func validateUsernamePassword(username: String, password: String) {
        viewState = LoginViewState(
            username: username,
            password: password,
            usernameError: (loginDataValidator.checkUsername(username: username) as? LoginDataValidator.LoginValidatorResultError)?.message ?? "",
            passwordError: (loginDataValidator.checkPassword(password: password) as? LoginDataValidator.LoginValidatorResultError)?.message ?? ""
        )
    }
    
    func login(username: String, password: String) async -> Void {
        viewState = LoginViewState(
            username: username,
            password: password,
            usernameError: "",
            passwordError: "",
            isLoading: true
        )
        
        do {
            let response = try await loginUseCase.invoke(username: username, password: password)
            
            if (response is ResourceSuccess) {
                viewState = LoginViewState(
                    username: response.data?.user.username ?? username,
                    password: password,
                    usernameError: "",
                    passwordError: "",
                    isLoading: false
                )
            } else {
                viewState = LoginViewState(
                    username: username,
                    password: password,
                    usernameError: "",
                    passwordError: "",
                    isLoading: false,
                    accessToken: "",
                    loginError: response.message ?? "something went wrong, please try again",
                    isLoginSuccess: false
                )
            }
            
            
        } catch {
            print(error)
            
            viewState = LoginViewState(
                username: username,
                password: password,
                usernameError: "",
                passwordError: "",
                isLoading: false,
                accessToken: "",
                loginError: error.localizedDescription,
                isLoginSuccess: false
            )
        }
    }
}
