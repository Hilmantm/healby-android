package com.healby.viewmodel

import com.healby.base.BaseResponse
import com.healby.base.BaseViewModel
import com.healby.model.TestingModel
import com.healby.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestingViewModel @Inject constructor(private val repository: NetworkRepository): BaseViewModel<TestingModel>() {
    override fun action() {
        print("Hallo")
        val resultTestingModel = TestingModel(
                hallo = "Hallo pak"
        )
        load.postValue(true)
        result.postValue(BaseResponse(success = true, 200, "", resultTestingModel))
        error.postValue("No Error")
    }
}