package com.healby.features.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.healby.R
import com.healby.features.onboarding.OnboardingActivity
import com.healby.utilities.Constant.Companion.SPLASHSCREEN_DELAY
import com.healby.utilities.Preferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Splashscreen : AppCompatActivity() {

    private lateinit var preferences: Preferences
    private var firsTimeInstall: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        preferences = Preferences(applicationContext)
        firsTimeInstall = preferences.getPreferenceFirstTimeInstall()

        Handler().postDelayed({
            if (firsTimeInstall) {
                val intentToOnboarding = Intent(this@Splashscreen, OnboardingActivity::class.java)
                startActivity(intentToOnboarding)
                finish()
            } else {
                // HAPUS KETIKA FITUR ONBOARDING SELESAI
                val intentToOnboarding = Intent(this@Splashscreen, OnboardingActivity::class.java)
                startActivity(intentToOnboarding)
                finish()            }
        }, SPLASHSCREEN_DELAY)
    }
}