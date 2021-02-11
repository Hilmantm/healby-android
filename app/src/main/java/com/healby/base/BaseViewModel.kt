package com.healby.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


abstract class BaseViewModel<T, M>: ViewModel() {

    protected val load: MutableLiveData<Boolean> = MutableLiveData(false)
    protected val result: MutableLiveData<BaseResponse<T>> = MutableLiveData()
    protected val error: MutableLiveData<M> = MutableLiveData()

    abstract fun action()

    fun getLoad(): LiveData<Boolean> = load

    fun getResult(): LiveData<BaseResponse<T>> = result

    fun getError(): LiveData<M> = error

}