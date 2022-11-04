package com.andremw96.notesnotes_kmm.model.repository.impl

import com.andremw96.notesnotes_kmm.model.repository.BaseRepository
import com.andremw96.notesnotes_kmm.model.repository.LoginRepository
import com.andremw96.notesnotes_kmm.network.NotesNotesService
import com.andremw96.notesnotes_kmm.network.model.login.LoginUserResponse
import com.andremw96.notesnotes_kmm.network.utils.Resource
import com.andremw96.notesnotes_kmm.sharedpreferences.NotesNotesPreferences
import com.andremw96.notesnotes_kmm.sharedpreferences.NotesNotesPreferences.Companion.ACCESS_TOKEN_KEY
import com.andremw96.notesnotes_kmm.sharedpreferences.NotesNotesPreferences.Companion.USERNAME_KEY

class LoginRepositoryImpl(
    private val notesNotesService: NotesNotesService,
    private val notesNotesPreferences: NotesNotesPreferences
) : LoginRepository, BaseRepository() {
    override suspend fun login(username: String, password: String): Resource<LoginUserResponse> {
        return safeApiCall {
            notesNotesService.login(username, password)
        }
    }

    override fun saveCredential(token: String, username: String) {
        notesNotesPreferences.setString(ACCESS_TOKEN_KEY, token)
        notesNotesPreferences.setString(USERNAME_KEY, username)
    }

    override fun getCredential(): Pair<String?, String?> {
        return Pair(
            notesNotesPreferences.getString(ACCESS_TOKEN_KEY),
            notesNotesPreferences.getString(USERNAME_KEY)
        )
    }
}
