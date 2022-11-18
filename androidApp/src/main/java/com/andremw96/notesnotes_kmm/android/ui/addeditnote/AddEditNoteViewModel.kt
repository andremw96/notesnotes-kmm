package com.andremw96.notesnotes_kmm.android.ui.addeditnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andremw96.notesnotes_kmm.model.repository.AddEditNoteDataValidator

class AddEditNoteViewModel(
    private val addEditNoteDataValidator: AddEditNoteDataValidator,
) : ViewModel() {
    private var _addEditState: MutableLiveData<AddEditNoteState> = MutableLiveData()
    val addEditState: LiveData<AddEditNoteState> = _addEditState

    fun addEditDataChanged(title: String, description: String) {
        var titleError: String? = null
        val checkTitle = addEditNoteDataValidator.checkTitle(title)
        if (checkTitle is AddEditNoteDataValidator.AddEditNoteValidatorResult.Error) {
            titleError = checkTitle.message
        }

        var descriptionError: String? = null
        val checkDescription = addEditNoteDataValidator.checkDescription(description)
        if (checkDescription is AddEditNoteDataValidator.AddEditNoteValidatorResult.Error) {
            descriptionError = checkDescription.message
        }

        _addEditState.postValue(
            AddEditNoteState(
                title = title,
                description = description,
                titleError = titleError,
                descriptionError = descriptionError,
            )
        )
    }
}
