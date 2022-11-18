package com.andremw96.notesnotes_kmm.model.repository.impl

import com.andremw96.notesnotes_kmm.domain.GetCredential
import com.andremw96.notesnotes_kmm.model.repository.AddEditNoteRepository
import com.andremw96.notesnotes_kmm.model.repository.BaseRepository
import com.andremw96.notesnotes_kmm.network.NotesNotesService
import com.andremw96.notesnotes_kmm.network.model.addeditnote.SaveNewNoteResponse
import com.andremw96.notesnotes_kmm.network.utils.Resource

class AddEditNoteRepositoryImpl(
    private val notesNotesService: NotesNotesService,
    private val getCredential: GetCredential,
) : AddEditNoteRepository, BaseRepository() {
    override suspend fun saveNewNote(
        title: String,
        description: String?
    ): Resource<SaveNewNoteResponse> {
        val credential = getCredential()
        return if (credential.userid != null) {
            safeApiCall {
                notesNotesService.saveNewNote(credential.userid, title, description)
            }
        } else {
            Resource.Error("user id not found")
        }
    }
}
