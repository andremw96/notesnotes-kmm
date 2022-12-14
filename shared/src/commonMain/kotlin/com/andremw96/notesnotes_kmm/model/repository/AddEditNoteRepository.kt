package com.andremw96.notesnotes_kmm.model.repository

import com.andremw96.notesnotes_kmm.network.model.addeditnote.SaveNewNoteResponse
import com.andremw96.notesnotes_kmm.network.model.addeditnote.UpdateNoteResponse
import com.andremw96.notesnotes_kmm.network.utils.Resource

interface AddEditNoteRepository {
    suspend fun saveNewNote(title: String, description: String?): Resource<SaveNewNoteResponse>
    suspend fun updateNote(noteId: Int, title: String, description: String?): Resource<UpdateNoteResponse>
}
