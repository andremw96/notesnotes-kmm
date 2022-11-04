package com.andremw96.notesnotes_kmm.model.repository.impl

import com.andremw96.notesnotes_kmm.model.Credential
import com.andremw96.notesnotes_kmm.model.repository.BaseRepository
import com.andremw96.notesnotes_kmm.model.repository.LoginRepository
import com.andremw96.notesnotes_kmm.network.NotesNotesService
import com.andremw96.notesnotes_kmm.network.model.login.LoginUserResponse
import com.andremw96.notesnotes_kmm.network.utils.Resource
import com.andremw96.notesnotes_kmm.sharedpreferences.NotesNotesPreferences
import com.andremw96.notesnotes_kmm.sharedpreferences.NotesNotesPreferences.Companion.ACCESS_TOKEN_KEY
import com.andremw96.notesnotes_kmm.sharedpreferences.NotesNotesPreferences.Companion.USERNAME_KEY
import com.andremw96.notesnotes_kmm.sharedpreferences.NotesNotesPreferences.Companion.USER_ID_KEY

class LoginRepositoryImpl(
    private val notesNotesService: NotesNotesService,
    private val notesNotesPreferences: NotesNotesPreferences
) : LoginRepository, BaseRepository() {
    override suspend fun login(username: String, password: String): Resource<LoginUserResponse> {
        return safeApiCall {
            notesNotesService.login(username, password)
        }
    }

    override fun saveCredential(token: String, username: String, userId: Int) {
        notesNotesPreferences.setString(ACCESS_TOKEN_KEY, token)
        notesNotesPreferences.setString(USERNAME_KEY, username)
        notesNotesPreferences.setInt(USER_ID_KEY, userId)
    }

    override fun getCredential(): Credential {
        return Credential(
            notesNotesPreferences.getString(ACCESS_TOKEN_KEY),
            notesNotesPreferences.getString(USERNAME_KEY),
            notesNotesPreferences.getInt(USER_ID_KEY)
        )
    }

    override fun getAccessToken(): String? {
        return notesNotesPreferences.getString(ACCESS_TOKEN_KEY)
    }
}
