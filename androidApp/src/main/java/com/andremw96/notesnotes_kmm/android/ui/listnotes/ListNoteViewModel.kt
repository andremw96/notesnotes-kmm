package com.andremw96.notesnotes_kmm.android.ui.listnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListNoteViewModel: ViewModel() {
    private var _listNoteState: MutableLiveData<ListNoteState> = MutableLiveData()
    val listNoteState: LiveData<ListNoteState> = _listNoteState
}
