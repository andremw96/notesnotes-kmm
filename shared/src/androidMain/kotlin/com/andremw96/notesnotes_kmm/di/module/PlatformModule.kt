package com.andremw96.notesnotes_kmm.di.module

import com.andremw96.notesnotes_kmm.sharedpreferences.NotesPreferencesFactory
import org.koin.dsl.module

actual val platformModule = module {
    single {
        NotesPreferencesFactory(get()).createNotesPreferences("andremw.notesnotes.preferences")
    }
}
