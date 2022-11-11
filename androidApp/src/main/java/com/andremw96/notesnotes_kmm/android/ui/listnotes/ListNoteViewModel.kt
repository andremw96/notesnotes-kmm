package com.andremw96.notesnotes_kmm.android.ui.listnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andremw96.notesnotes_kmm.android.model.NoteItem
import com.andremw96.notesnotes_kmm.domain.DeleteNote
import com.andremw96.notesnotes_kmm.domain.FetchListNote
import com.andremw96.notesnotes_kmm.domain.Logout
import com.andremw96.notesnotes_kmm.network.utils.Resource
import kotlinx.coroutines.launch

class ListNoteViewModel(
    private val fetchListNote: FetchListNote,
    private val logout: Logout,
    private val deleteNote: DeleteNote,
) : ViewModel() {
    private var _listNoteState: MutableLiveData<ListNoteState> = MutableLiveData()
    val listNoteState: LiveData<ListNoteState> = _listNoteState

    fun logoutFromApps() {
        viewModelScope.launch {
            logout()
        }
    }

    fun fetchData() {
        viewModelScope.launch {
            when (val result = fetchListNote()) {
                is Resource.Error -> _listNoteState.postValue(
                    ListNoteState(
                        isLoading = false,
                        listData = listOf(),
                        error = result.message
                    )
                )
                is Resource.Loading -> _listNoteState.postValue(
                    ListNoteState(
                        isLoading = true,
                        error = null,
                        listData = listOf(),
                    )
                )
                is Resource.Success -> _listNoteState.postValue(
                    ListNoteState(
                        isLoading = false,
                        error = null,
                        listData = result.data?.map {
                            NoteItem.schemaToItem(it)
                        } ?: listOf()
                    )
                )
            }
        }
    }

    fun deleteNoteData(note: NoteItem) {
        viewModelScope.launch {
            when (val result = deleteNote(note.id)) {
                is Resource.Error -> _listNoteState.postValue(
                    ListNoteState(
                        isLoading = false,
                        error = result.message
                    )
                )
                is Resource.Loading -> _listNoteState.postValue(
                    ListNoteState(
                        isLoading = true,
                        error = null,
                    )
                )
                is Resource.Success -> {
                    val newDataState = _listNoteState.value?.listData?.toMutableList()
                    newDataState?.remove(note)

                    _listNoteState.postValue(
                        ListNoteState(
                            isLoading = false,
                            error = null,
                            listData = newDataState ?: listOf()
                        )
                    )
                }
            }
        }
    }
}
