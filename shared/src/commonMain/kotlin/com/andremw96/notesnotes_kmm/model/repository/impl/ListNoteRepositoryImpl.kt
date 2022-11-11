package com.andremw96.notesnotes_kmm.model.repository.impl

import com.andremw96.notesnotes_kmm.domain.GetCredential
import com.andremw96.notesnotes_kmm.model.repository.BaseRepository
import com.andremw96.notesnotes_kmm.model.repository.ListNoteRepository
import com.andremw96.notesnotes_kmm.network.NotesNotesService
import com.andremw96.notesnotes_kmm.network.model.listnotes.response.DeleteNoteResponse
import com.andremw96.notesnotes_kmm.network.model.listnotes.response.ListNoteResponseItem
import com.andremw96.notesnotes_kmm.network.utils.Resource

class ListNoteRepositoryImpl(
    private val notesNotesService: NotesNotesService,
    private val getCredential: GetCredential,
) : ListNoteRepository, BaseRepository() {
    override suspend fun fetchData(): Resource<List<ListNoteResponseItem>> {
        val credential = getCredential()
        return if (credential.userid != null) {
             safeApiCall {
                notesNotesService.fetchListNotes(credential.userid)
            }
        } else {
            Resource.Error("user id not found")
        }
    }

    override suspend fun deleteNote(noteId: Int): Resource<DeleteNoteResponse> {
        val credential = getCredential()
        return if (credential.userid != null) {
            safeApiCall {
                notesNotesService.deleteNote(credential.userid, noteId)
            }
        } else {
            Resource.Error("user id not found")
        }
    }
}
