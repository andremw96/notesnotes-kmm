package com.andremw96.notesnotes_kmm.android

import android.support.multidex.MultiDexApplication
import com.andremw96.notesnotes_kmm.android.di.appModule
import com.andremw96.notesnotes_kmm.di.module.repositoryModule
import com.andremw96.notesnotes_kmm.di.module.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NotesNotesApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NotesNotesApplication)
            androidLogger()
            modules(appModule + repositoryModule + useCaseModule)
        }
    }
}
