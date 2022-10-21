package com.andremw96.notesnotes_kmm.model.repository.impl

import com.andremw96.notesnotes_kmm.model.repository.LoginRepository
import com.andremw96.notesnotes_kmm.network.NotesNotesService

class LoginRepositoryImpl(
    private val notesNotesService: NotesNotesService,
) : LoginRepository {
    override suspend fun login(username: String, password: String): String {
        return notesNotesService.login(username, password)
    }
}
