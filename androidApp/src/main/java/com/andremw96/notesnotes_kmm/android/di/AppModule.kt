package com.andremw96.notesnotes_kmm.android.di

import com.andremw96.notesnotes_kmm.android.ui.addeditnote.AddEditNoteViewModel
import com.andremw96.notesnotes_kmm.android.ui.listnotes.ListNoteViewModel
import com.andremw96.notesnotes_kmm.android.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        LoginViewModel(get(), get(), get(), get())
    }
    viewModel {
        ListNoteViewModel(get(), get(), get())
    }
    viewModel {
        AddEditNoteViewModel(get(), get())
    }
}
