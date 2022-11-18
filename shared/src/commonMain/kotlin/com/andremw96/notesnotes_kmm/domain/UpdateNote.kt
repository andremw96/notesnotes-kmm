package com.andremw96.notesnotes_kmm.domain

import com.andremw96.notesnotes_kmm.domain.model.UpdateNoteSchema
import com.andremw96.notesnotes_kmm.network.utils.Resource

interface UpdateNote {
    suspend fun invoke(noteId: Int, title: String, description: String?): Resource<UpdateNoteSchema>
}
