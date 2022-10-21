package com.andremw96.notesnotes_kmm.di.module

import com.andremw96.notesnotes_kmm.model.repository.LoginRepository
import com.andremw96.notesnotes_kmm.model.repository.impl.LoginRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<LoginRepository>{
        LoginRepositoryImpl(get())
    }
}
