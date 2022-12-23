//
//  UseCaseProvider.swift
//  iosApp
//
//  Created by Quipper Indonesia on 23/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class UseCaseProvider {
    private static let instance = InjectionHelper()
    
    let useCaseProvider = UseCaseProvider.getInstance()
    
    static func getInstance() -> InjectionHelper {
        return instance
    }
}
