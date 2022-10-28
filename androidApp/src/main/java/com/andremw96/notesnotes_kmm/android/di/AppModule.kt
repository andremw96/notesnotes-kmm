package com.andremw96.notesnotes_kmm.android.di

import com.andremw96.notesnotes_kmm.android.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        LoginViewModel(get(), get())
    }
}