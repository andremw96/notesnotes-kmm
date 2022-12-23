package com.andremw96.notesnotes_kmm.android

import androidx.multidex.MultiDexApplication
import com.andremw96.notesnotes_kmm.android.di.androidViewModelModule
import com.andremw96.notesnotes_kmm.di.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NotesNotesApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NotesNotesApplication)
            androidLogger()
            modules(
                androidViewModelModule +
                appModule()
            )
        }
    }
}
