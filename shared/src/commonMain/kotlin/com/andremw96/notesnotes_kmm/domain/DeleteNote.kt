package com.andremw96.notesnotes_kmm.domain

import com.andremw96.notesnotes_kmm.domain.model.DeleteNoteSchema
import com.andremw96.notesnotes_kmm.network.utils.Resource

interface DeleteNote {
    suspend operator fun invoke(noteId: Int): Resource<DeleteNoteSchema>
}
