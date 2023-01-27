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
}
