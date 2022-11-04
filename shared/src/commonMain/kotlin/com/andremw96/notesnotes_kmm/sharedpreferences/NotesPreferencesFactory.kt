package com.andremw96.notesnotes_kmm.sharedpreferences

expect class NotesPreferencesFactory {
    fun createNotesPreferences(prefName: String): NotesNotesPreferences
}
