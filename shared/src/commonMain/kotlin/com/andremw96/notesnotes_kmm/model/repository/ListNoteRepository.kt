package com.andremw96.notesnotes_kmm.model.repository

import com.andremw96.notesnotes_kmm.network.model.listnotes.ListNoteResponseItem
import com.andremw96.notesnotes_kmm.network.utils.Resource

interface ListNoteRepository {
    suspend fun fetchData(): Resource<List<ListNoteResponseItem>>
}
