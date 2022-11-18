package com.andremw96.notesnotes_kmm.domain.impl

import com.andremw96.notesnotes_kmm.domain.SaveNewNote
import com.andremw96.notesnotes_kmm.domain.model.SaveNewNoteSchema
import com.andremw96.notesnotes_kmm.model.repository.AddEditNoteRepository
import com.andremw96.notesnotes_kmm.network.utils.Resource

class SaveNewNoteImpl(
    private val addEditNoteRepository: AddEditNoteRepository,
) : SaveNewNote {
    override suspend fun invoke(title: String, description: String?): Resource<SaveNewNoteSchema> {
        return SaveNewNoteSchema.responseToSchema(addEditNoteRepository.saveNewNote(title, description))
    }
}
