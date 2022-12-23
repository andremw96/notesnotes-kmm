//
//  ViewModelProvider.swift
//  iosApp
//
//  Created by Quipper Indonesia on 23/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared


class ViewModelProvider {
    private static let instance = ViewModelProvider()
    
    let useCaseProvider = UseCaseProvider.getInstance()
    
    static func getInstance() -> ViewModelProvider {
        return instance
    }
    
    @MainActor func provideLoginViewModel() -> LoginViewModel {
        let loginDataValidator = useCaseProvider.loginDataValidator
        let loginUsecase = useCaseProvider.loginUseCase
        
        return LoginViewModel(loginDataValidator: loginDataValidator, loginUseCase: loginUsecase)
    }
    
}
