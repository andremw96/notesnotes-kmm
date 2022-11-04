package com.andremw96.notesnotes_kmm.sharedpreferences

import android.app.Application

actual class NotesPreferencesFactory(private val application: Application) {
    actual fun createNotesPreferences(prefName: String): NotesNotesPreferences {
        return NotesPreferencesImpl(application, prefName)
    }
}
