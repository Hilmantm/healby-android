package com.healby.features.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.healby.R
import com.healby.utilities.Preferences

class OnboardingActivity : AppCompatActivity() {

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        preferences = Preferences(applicationContext)
        preferences.setPreferenceFirstTimeInstall(false)
    }
}