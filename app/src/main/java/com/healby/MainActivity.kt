package com.healby

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.Observer
import com.healby.model.TestingModel
import com.healby.viewmodel.TestingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val testingViewModel: TestingViewModel by viewModels()

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testingViewModel.action()
        testingViewModel.getLoad()
        testingViewModel.getResult().observe(this, Observer {
            Log.d(TAG, "testing view model observer: ${it.data.hallo}")
        })
    }
}