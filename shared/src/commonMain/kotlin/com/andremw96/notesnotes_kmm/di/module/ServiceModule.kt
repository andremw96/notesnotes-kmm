package com.andremw96.notesnotes_kmm.di.module

import com.andremw96.notesnotes_kmm.network.NotesNotesService
import com.andremw96.notesnotes_kmm.network.NotesNotesServiceImpl
import org.koin.dsl.module

val serviceModule = module {
    single<NotesNotesService> {
        NotesNotesServiceImpl()
    }
}
