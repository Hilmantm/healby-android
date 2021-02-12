package com.healby.utilities

import android.content.Context

class Preferences(ctx: Context) {

    companion object {
        const val PREFS_APP = "prefs_app"
        const val FIRST_TIME_INSTALL = "first_time_install"
    }

    private val preferences = ctx.getSharedPreferences(PREFS_APP, Context.MODE_PRIVATE)

    fun setPreferenceFirstTimeInstall(value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(FIRST_TIME_INSTALL, value)
        editor.apply()
    }

    fun getPreferenceFirstTimeInstall(): Boolean {
        return preferences.getBoolean(FIRST_TIME_INSTALL, true)
    }

}