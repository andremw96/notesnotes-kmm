package com.andremw96.notesnotes_kmm.di.module

import com.andremw96.notesnotes_kmm.domain.GetCredential
import com.andremw96.notesnotes_kmm.domain.Login
import com.andremw96.notesnotes_kmm.domain.SaveCredential
import com.andremw96.notesnotes_kmm.model.repository.LoginDataValidator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(appModule())
    }
}

class InjectionHelper : KoinComponent {
    val loginDataValidator: LoginDataValidator by inject()
    val loginUseCase: Login by inject()
    val saveCredential: SaveCredential by inject()
    val getCredential: GetCredential by inject()
}
