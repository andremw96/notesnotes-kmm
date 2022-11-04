package com.andremw96.notesnotes_kmm.domain.impl

import com.andremw96.notesnotes_kmm.domain.FetchListNote
import com.andremw96.notesnotes_kmm.domain.model.ListNoteSchema
import com.andremw96.notesnotes_kmm.model.repository.ListNoteRepository
import com.andremw96.notesnotes_kmm.network.model.listnotes.ListNoteResponseItem
import com.andremw96.notesnotes_kmm.network.utils.Resource

class FetchListNoteImpl(
    private val listNoteRepository: ListNoteRepository,
) : FetchListNote {
    override suspend fun invoke(): Resource<List<ListNoteSchema>> {
        return ListNoteSchema.responseToSchema(listNoteRepository.fetchData())
    }
}

