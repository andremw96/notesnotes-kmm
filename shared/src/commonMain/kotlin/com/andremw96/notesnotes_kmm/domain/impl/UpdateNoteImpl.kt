package com.andremw96.notesnotes_kmm.domain.impl

import com.andremw96.notesnotes_kmm.domain.UpdateNote
import com.andremw96.notesnotes_kmm.domain.model.UpdateNoteSchema
import com.andremw96.notesnotes_kmm.model.repository.AddEditNoteRepository
import com.andremw96.notesnotes_kmm.network.utils.Resource

class UpdateNoteImpl(
    private val addEditNoteRepository: AddEditNoteRepository,
) : UpdateNote {
    override suspend fun invoke(
        noteId: Int,
        title: String,
        description: String?
    ): Resource<UpdateNoteSchema> {
        return UpdateNoteSchema.responseToSchema(
            addEditNoteRepository.updateNote(
                noteId = noteId,
                title = title,
                description = description
            )
        )
    }
}
