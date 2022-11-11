package com.andremw96.notesnotes_kmm.domain.impl

import com.andremw96.notesnotes_kmm.domain.DeleteNote
import com.andremw96.notesnotes_kmm.domain.model.DeleteNoteSchema
import com.andremw96.notesnotes_kmm.model.repository.ListNoteRepository
import com.andremw96.notesnotes_kmm.network.utils.Resource

class DeleteNoteImpl(
    private val noteRepository: ListNoteRepository,
) : DeleteNote {
    override suspend fun invoke(noteId: Int): Resource<DeleteNoteSchema> {
        return DeleteNoteSchema.responseToSchema(noteRepository.deleteNote(noteId))
    }
}
