package com.andremw96.notesnotes_kmm.android.ui.listnotes

data class ListNoteState(
    val isLoading: Boolean = false,
    val listData: List<String> = listOf(),
    val error: String? = null,
)
