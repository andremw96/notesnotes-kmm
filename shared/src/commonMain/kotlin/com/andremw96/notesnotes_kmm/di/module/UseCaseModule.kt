package com.andremw96.notesnotes_kmm.di.module

import com.andremw96.notesnotes_kmm.domain.*
import com.andremw96.notesnotes_kmm.domain.impl.*
import com.andremw96.notesnotes_kmm.model.repository.LoginDataValidator
import org.koin.dsl.module

val useCaseModule = module {
    single<Login> { LoginImpl(get()) }
    single { LoginDataValidator() }
    single<SaveCredential> { SaveCredentialImpl(get()) }
    single<GetCredential> { GetCredentialImpl(get()) }
    single<GetAccessToken> { GetAccessTokenImpl(get()) }
    single<FetchListNote> { FetchListNoteImpl(get()) }
}
