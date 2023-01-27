//
//  SignupViewModel.swift
//  iosApp
//
//  Created by Quipper Indonesia on 27/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor class SignupViewModel: ObservableObject {
    @Published var viewState: SignupViewState = SignupViewState()
    
    private let signupDataValidator: SignupDataValidator
    private let signupUseCase: Signup
    private let loginUseCase: Login
    private let saveCredential: SaveCredential
    
    init(signupDataValidator: SignupDataValidator, signupUseCase: Signup, loginUseCase: Login, saveCredential: SaveCredential) {
        self.signupDataValidator = signupDataValidator
        self.signupUseCase = signupUseCase
        self.loginUseCase = loginUseCase
        self.saveCredential = saveCredential
    }
    
    func signupDataChanged(email: String, username: String, password: String, confirmationPassword: String) {
        viewState = SignupViewState(
            email: email,
            username: username,
            password: password,
            confirmationPassword: confirmationPassword,
            usernameError: (signupDataValidator.checkUsername(username: username) as? SignupDataValidator.SignupValidatorResultError)?.message ?? "",
            passwordError: (signupDataValidator.checkPassword(password: password) as? SignupDataValidator.SignupValidatorResultError)?.message ?? "", emailError: (signupDataValidator.checkEmail(email: email) as? SignupDataValidator.SignupValidatorResultError)?.message ?? "",
            confirmationPasswordError: (signupDataValidator.checkConfirmationPassword(password: password, confirmationPassword: confirmationPassword) as? SignupDataValidator.SignupValidatorResultError)?.message ?? ""
        )
    }
    
    func signup(email: String, username: String, password: String, confirmationPassword: String, onSignupSuccess: () -> Void) async -> Void {
        viewState = SignupViewState(
            email: email,
            username: username,
            password: password,
            confirmationPassword: confirmationPassword,
            usernameError: "",
            passwordError: "",
            emailError: "",
            confirmationPasswordError: "",
            isLoading: true
        )
        
        if (viewState.isDataValid) {
            do {
                let response = try await signupUseCase.invoke(email: email, username: username, password: password)
                    
                if (response is ResourceSuccess) {
                    viewState = SignupViewState(
                        email: email,
                        username: username,
                        password: password,
                        confirmationPassword: confirmationPassword,
                        usernameError: "",
                        passwordError: "",
                        emailError: "",
                        confirmationPasswordError: "",
                        isLoading: false,
                        signupError: ""
                    )
                    
                    viewState = SignupViewState(
                        email: email,
                        username: username,
                        password: password,
                        isLoading: true,
                        isLoginSuccess: false
                    )
                    
                    let login = try await loginUseCase.invoke(username: username, password: password)
                    
                    if (login is ResourceSuccess) {
                        viewState = SignupViewState(
                            email: email,
                            username: username,
                            password: password,
                            confirmationPassword: confirmationPassword,
                            usernameError: "",
                            passwordError: "",
                            emailError: "",
                            confirmationPasswordError: "",
                            isLoading: false,
                            signupError: "",
                            isLoginSuccess: true
                        )
                        
                        if login.data != nil {
                            try await saveCredential.invoke(username: username, token: login.data!.accessToken, userId: (login.data?.user.userId)!)
                            onSignupSuccess()
                        }
                    } else {
                        viewState = SignupViewState(
                            email: email,
                            username: username,
                            password: password,
                            confirmationPassword: confirmationPassword,
                            usernameError: "",
                            passwordError: "",
                            emailError: "",
                            confirmationPasswordError: "",
                            isLoading: false,
                            signupError: response.message ?? "something went wrong, please try again",
                            isLoginSuccess: false
                        )
                    }
                } else {
                    viewState = SignupViewState(
                        email: email,
                        username: username,
                        password: password,
                        confirmationPassword: confirmationPassword,
                        usernameError: "",
                        passwordError: "",
                        emailError: "",
                        confirmationPasswordError: "",
                        isLoading: false,
                        signupError: response.message ?? "something went wrong, please try again",
                        isLoginSuccess: false
                    )
                }
            } catch {
                print(error)
                
                viewState = SignupViewState(
                    email: email,
                    username: username,
                    password: password,
                    confirmationPassword: confirmationPassword,
                    usernameError: "",
                    passwordError: "",
                    emailError: "",
                    confirmationPasswordError: "",
                    isLoading: false,
                    signupError: error.localizedDescription,
                    isLoginSuccess: false
                )
            }
        }
    }
}
