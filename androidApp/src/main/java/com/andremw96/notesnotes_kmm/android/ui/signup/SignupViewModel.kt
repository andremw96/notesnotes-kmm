package com.andremw96.notesnotes_kmm.android.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel: ViewModel() {
    private var _signupState: MutableLiveData<SignupState> = MutableLiveData()
    val signupState: LiveData<SignupState> = _signupState
}
