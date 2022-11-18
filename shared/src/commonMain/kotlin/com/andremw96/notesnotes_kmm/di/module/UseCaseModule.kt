package com.andremw96.notesnotes_kmm.di.module

import com.andremw96.notesnotes_kmm.domain.*
import com.andremw96.notesnotes_kmm.domain.impl.*
import com.andremw96.notesnotes_kmm.model.repository.AddEditNoteDataValidator
import com.andremw96.notesnotes_kmm.model.repository.LoginDataValidator
import org.koin.dsl.module

val useCaseModule = module {
    single<Login> { LoginImpl(get()) }
    single<Signup> { SignupImpl(get()) }
    single { LoginDataValidator() }
    single { AddEditNoteDataValidator() }
    single<SaveCredential> { SaveCredentialImpl(get()) }
    single<GetCredential> { GetCredentialImpl(get()) }
    single<GetAccessToken> { GetAccessTokenImpl(get()) }
    single<Logout> { LogoutImpl(get()) }
    single<FetchListNote> { FetchListNoteImpl(get()) }
    single<DeleteNote> { DeleteNoteImpl(get()) }
    single<SaveNewNote> { SaveNewNoteImpl(get()) }
    single<UpdateNote> { UpdateNoteImpl(get()) }
}
