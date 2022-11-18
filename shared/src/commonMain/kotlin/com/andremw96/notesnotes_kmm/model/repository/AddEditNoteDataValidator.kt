package com.andremw96.notesnotes_kmm.model.repository

class AddEditNoteDataValidator {
    sealed class AddEditNoteValidatorResult {
        object Success : AddEditNoteValidatorResult()
        class Error(val message: String) : AddEditNoteValidatorResult()
    }

    fun checkTitle(title: String): AddEditNoteValidatorResult {
        return if (isTitleValid(title)) AddEditNoteValidatorResult.Success else AddEditNoteValidatorResult.Error(
            "Title is not valid"
        )
    }

    fun checkDescription(description: String): AddEditNoteValidatorResult {
        return when {
            description.length < 5 -> AddEditNoteValidatorResult.Error("Description must be >5 characters")
            else -> AddEditNoteValidatorResult.Success
        }
    }

    private fun isTitleValid(title: String) = title.isNotBlank() && title.isNotEmpty()
}
