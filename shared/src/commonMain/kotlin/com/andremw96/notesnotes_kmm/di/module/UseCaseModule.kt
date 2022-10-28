package com.andremw96.notesnotes_kmm.di.module

import com.andremw96.notesnotes_kmm.domain.Login
import com.andremw96.notesnotes_kmm.domain.impl.LoginImpl
import com.andremw96.notesnotes_kmm.model.repository.LoginDataValidator
import org.koin.dsl.module

val useCaseModule = module {
    single<Login> {
        LoginImpl(get())
    }
    single {
        LoginDataValidator()
    }
}
