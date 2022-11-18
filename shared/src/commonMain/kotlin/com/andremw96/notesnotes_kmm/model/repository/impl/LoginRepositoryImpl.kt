package com.andremw96.notesnotes_kmm.model.repository.impl

import com.andremw96.notesnotes_kmm.model.repository.BaseRepository
import com.andremw96.notesnotes_kmm.model.repository.LoginRepository
import com.andremw96.notesnotes_kmm.network.NotesNotesService
import com.andremw96.notesnotes_kmm.network.model.login.response.LoginUserResponse
import com.andremw96.notesnotes_kmm.network.model.signup.SignupResponse
import com.andremw96.notesnotes_kmm.network.utils.Resource

class LoginRepositoryImpl(
    private val notesNotesService: NotesNotesService,
) : LoginRepository, BaseRepository() {
    override suspend fun login(username: String, password: String): Resource<LoginUserResponse> {
        return safeApiCall {
            notesNotesService.login(username, password)
        }
    }

    override suspend fun signup(
        email: String,
        username: String,
        password: String
    ): Resource<SignupResponse> {
        return safeApiCall {
            notesNotesService.signup(email, username, password)
        }
    }
}
