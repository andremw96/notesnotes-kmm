package com.andremw96.notesnotes_kmm.di.module

import com.andremw96.notesnotes_kmm.model.repository.AddEditNoteRepository
import com.andremw96.notesnotes_kmm.model.repository.CredentialRepository
import com.andremw96.notesnotes_kmm.model.repository.ListNoteRepository
import com.andremw96.notesnotes_kmm.model.repository.LoginRepository
import com.andremw96.notesnotes_kmm.model.repository.impl.AddEditNoteRepositoryImpl
import com.andremw96.notesnotes_kmm.model.repository.impl.CredentialRepositoryImpl
import com.andremw96.notesnotes_kmm.model.repository.impl.ListNoteRepositoryImpl
import com.andremw96.notesnotes_kmm.model.repository.impl.LoginRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<LoginRepository> {
        LoginRepositoryImpl(get())
    }

    single<CredentialRepository> {
        CredentialRepositoryImpl(get())
    }

    single<ListNoteRepository> {
        ListNoteRepositoryImpl(get(), get())
    }

    single<AddEditNoteRepository> {
        AddEditNoteRepositoryImpl(get(), get())
    }
}
