package com.andremw96.notesnotes_kmm.android.ui.addeditnote

/**
 * Data validation state of the login form.
 */
data class AddEditNoteState(
    val noteId: Int = -1,
    val title: String = "",
    val description: String = "",
    val titleError: String? = null,
    val descriptionError: String? = null,
    val isLoading: Boolean = false,
    val isNewNote: Boolean = false,
    val saveNoteSuccess: Boolean = false,
    val saveNoteError: String? = null,
) {
    val isDataValid: Boolean
        get() = titleError == null && descriptionError == null && title.isNotEmpty() && description.isNotEmpty()
}
