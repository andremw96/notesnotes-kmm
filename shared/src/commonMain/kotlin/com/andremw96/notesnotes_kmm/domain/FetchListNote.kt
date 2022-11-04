package com.andremw96.notesnotes_kmm.domain

import com.andremw96.notesnotes_kmm.domain.model.ListNoteSchema
import com.andremw96.notesnotes_kmm.network.utils.Resource

interface FetchListNote {
    suspend operator fun invoke(): Resource<List<ListNoteSchema>>
}
