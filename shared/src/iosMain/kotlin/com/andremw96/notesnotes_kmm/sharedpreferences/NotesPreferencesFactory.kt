package com.andremw96.notesnotes_kmm.sharedpreferences

actual class NotesPreferencesFactory {
    actual fun createNotesPreferences(prefName: String): NotesNotesPreferences {
        return NotesPreferencesImpl(prefName)
    }
}
