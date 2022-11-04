package com.andremw96.notesnotes_kmm.android.ui.listnotes

import com.andremw96.notesnotes_kmm.domain.model.ListNoteSchema

data class ListNoteState(
    val isLoading: Boolean = false,
    val listData: List<ListNoteSchema> = listOf(),
    val error: String? = null,
)
