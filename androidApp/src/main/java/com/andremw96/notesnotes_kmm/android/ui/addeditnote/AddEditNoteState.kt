package com.andremw96.notesnotes_kmm.android.ui.addeditnote

/**
 * Data validation state of the login form.
 */
data class AddEditNoteState(
    val title: String = "",
    val description: String = "",
    val titleError: String? = null,
    val descriptionError: String? = null,
    val isLoading: Boolean = false,
    val isNewNote: Boolean = false
) {
    val isDataValid: Boolean
        get() = titleError == null && descriptionError == null && title.isNotEmpty() && description.isNotEmpty()
}