package com.healby.features.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.healby.R
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
                Toast.makeText(this@Splashscreen, "First Time Install", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@Splashscreen, "Not First Time Install", Toast.LENGTH_SHORT).show()
            }
        }, SPLASHSCREEN_DELAY)
    }
}