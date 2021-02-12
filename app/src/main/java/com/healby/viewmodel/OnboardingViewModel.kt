package com.healby.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnboardingViewModel: ViewModel() {

    private val onboardingPosition: MutableLiveData<Int> = MutableLiveData()

    fun setPosition(position: Int) {
        onboardingPosition.postValue(position)
    }

    fun getOnboardingPosition(): LiveData<Int> = onboardingPosition

}