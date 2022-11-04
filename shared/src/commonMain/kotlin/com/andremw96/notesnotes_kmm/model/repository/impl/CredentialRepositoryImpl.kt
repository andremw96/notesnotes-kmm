package com.andremw96.notesnotes_kmm.model.repository.impl

import com.andremw96.notesnotes_kmm.model.Credential
import com.andremw96.notesnotes_kmm.model.repository.CredentialRepository
import com.andremw96.notesnotes_kmm.sharedpreferences.NotesNotesPreferences

class CredentialRepositoryImpl(
    private val notesNotesPreferences: NotesNotesPreferences
) : CredentialRepository {
    override fun saveCredential(token: String, username: String, userId: Int) {
        notesNotesPreferences.setString(NotesNotesPreferences.ACCESS_TOKEN_KEY, token)
        notesNotesPreferences.setString(NotesNotesPreferences.USERNAME_KEY, username)
        notesNotesPreferences.setInt(NotesNotesPreferences.USER_ID_KEY, userId)
    }

    override fun getCredential(): Credential {
        return Credential(
            notesNotesPreferences.getString(NotesNotesPreferences.ACCESS_TOKEN_KEY),
            notesNotesPreferences.getString(NotesNotesPreferences.USERNAME_KEY),
            notesNotesPreferences.getInt(NotesNotesPreferences.USER_ID_KEY)
        )
    }

    override fun getAccessToken(): String? {
        return notesNotesPreferences.getString(NotesNotesPreferences.ACCESS_TOKEN_KEY)
    }
}
