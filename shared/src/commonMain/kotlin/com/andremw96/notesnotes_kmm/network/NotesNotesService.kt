package com.andremw96.notesnotes_kmm.network

interface NotesNotesService {
    suspend fun login(username: String, password: String): String
}
