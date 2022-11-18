package com.andremw96.notesnotes_kmm.android.ui.addeditnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andremw96.notesnotes_kmm.android.model.NoteItem
import com.andremw96.notesnotes_kmm.domain.SaveNewNote
import com.andremw96.notesnotes_kmm.model.repository.AddEditNoteDataValidator
import com.andremw96.notesnotes_kmm.network.utils.Resource
import kotlinx.coroutines.launch

class AddEditNoteViewModel(
    private val addEditNoteDataValidator: AddEditNoteDataValidator,
    private val saveNewNote: SaveNewNote,
) : ViewModel() {
    private var _addEditState: MutableLiveData<AddEditNoteState> = MutableLiveData()
    val addEditState: LiveData<AddEditNoteState> = _addEditState

    fun initState(noteItem: NoteItem?) {
        _addEditState.postValue(
            AddEditNoteState(
                title = noteItem?.title ?: "",
                description = noteItem?.description ?: "",
                isNewNote = noteItem == null || noteItem.title == ""
            )
        )
    }

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

        val previousState = _addEditState.value
        _addEditState.postValue(
            AddEditNoteState(
                title = title,
                description = description,
                titleError = titleError,
                descriptionError = descriptionError,
                isNewNote = previousState?.isNewNote ?: false
            )
        )
    }

    fun saveNote(title: String, description: String) {
        val previousState = _addEditState.value
        viewModelScope.launch {
            _addEditState.postValue(
                AddEditNoteState(
                    title = title,
                    description = description,
                    isLoading = true,
                    saveNoteError = null,
                    isNewNote = previousState?.isNewNote ?: false,
                    saveNoteSuccess = false,
                )
            )

            if (_addEditState.value?.isDataValid == true) {
                val saveNewNote = saveNewNote.invoke(title, description)
                if (saveNewNote is Resource.Success) {
                    _addEditState.postValue(
                        AddEditNoteState(
                            title = title,
                            description = description,
                            isLoading = false,
                            saveNoteError = null,
                            isNewNote = previousState?.isNewNote ?: false,
                            saveNoteSuccess = true,
                        )
                    )
                } else {
                    _addEditState.postValue(
                        AddEditNoteState(
                            title = title,
                            description = description,
                            isLoading = false,
                            saveNoteError = saveNewNote.message,
                            isNewNote = previousState?.isNewNote ?: false,
                            saveNoteSuccess = false,
                        )
                    )
                }
            }
        }
    }
}
