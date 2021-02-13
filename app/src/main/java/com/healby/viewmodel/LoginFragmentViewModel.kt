package com.healby.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// CHANGE VIEW MODEL EXTEND CLASS IF THIS CLASS HAS REQUEST TO THE NETWORK
@HiltViewModel
class LoginFragmentViewModel @Inject constructor(): ViewModel() {

    private var passwordVisibility: MutableLiveData<Boolean> = MutableLiveData(false)

    fun setPasswordVisibility(visibility: Boolean) {
        passwordVisibility.postValue(visibility)
    }

    fun getPasswordVisibility(): LiveData<Boolean> = passwordVisibility

}