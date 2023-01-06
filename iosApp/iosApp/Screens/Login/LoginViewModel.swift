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
    private let saveCredential: SaveCredential
    private let getCredential: GetCredential
    
    init(loginDataValidator: LoginDataValidator, loginUseCase: Login, saveCredential: SaveCredential, getCredential: GetCredential) {
        self.loginDataValidator = loginDataValidator
        self.loginUseCase = loginUseCase
        self.saveCredential = saveCredential
        self.getCredential = getCredential
    }
    
    func validateUsernamePassword(username: String, password: String) {
        viewState = LoginViewState(
            username: username,
            password: password,
            usernameError: (loginDataValidator.checkUsername(username: username) as? LoginDataValidator.LoginValidatorResultError)?.message ?? "",
            passwordError: (loginDataValidator.checkPassword(password: password) as? LoginDataValidator.LoginValidatorResultError)?.message ?? ""
        )
    }
    
    func checkLogin(onAlreadyLogin: () -> Void) async {
        do {
            let credential = try await getCredential.invoke()
            if (credential.accessToken != "" && credential.username != "" && credential.userid != -1) {
                onAlreadyLogin()
            }
        } catch {
            print(error)
        }
    }
    
    func login(username: String, password: String, onLoginSuccess: () -> Void) async -> Void {
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
                    isLoading: false,
                    accessToken: response.data?.accessToken ?? "",
                    loginError: "",
                    isLoginSuccess: true
                )
                
                if response.data != nil {
                    try await saveCredential.invoke(username: username, token: response.data!.accessToken, userId: (response.data?.user.userId)!)
                    onLoginSuccess()
                }
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
