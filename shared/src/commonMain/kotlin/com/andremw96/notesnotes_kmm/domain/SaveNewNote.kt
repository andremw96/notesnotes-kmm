package com.andremw96.notesnotes_kmm.domain

import com.andremw96.notesnotes_kmm.domain.model.SaveNewNoteSchema
import com.andremw96.notesnotes_kmm.network.utils.Resource

interface SaveNewNote {
    suspend fun invoke(title: String, description: String?): Resource<SaveNewNoteSchema>
}
