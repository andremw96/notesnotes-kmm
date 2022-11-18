package com.andremw96.notesnotes_kmm.network

import io.ktor.client.statement.*

interface NotesNotesService {
    suspend fun login(username: String, password: String): HttpResponse
    suspend fun fetchListNotes(userId: Int): HttpResponse
    suspend fun deleteNote(userId: Int, noteId: Int): HttpResponse
    suspend fun saveNewNote(userId: Int, title: String, description: String?): HttpResponse
}
