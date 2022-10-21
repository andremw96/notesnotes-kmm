package com.andremw96.notesnotes_kmm.di.module

import com.andremw96.notesnotes_kmm.model.LoginRepository
import com.andremw96.notesnotes_kmm.model.impl.LoginRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    single<LoginRepository>{
        LoginRepositoryImpl()
    }
}
