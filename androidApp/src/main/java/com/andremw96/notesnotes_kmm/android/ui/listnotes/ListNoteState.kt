package com.andremw96.notesnotes_kmm.android.ui.listnotes

import com.andremw96.notesnotes_kmm.android.model.NoteItem

data class ListNoteState(
    val isLoading: Boolean = false,
    val listData: List<NoteItem> = listOf(),
    val error: String? = null,
)
