package com.example.greenscene.model.preference

import android.content.Context
import com.example.greenscene.model.AppPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppPreferenceImpl @Inject constructor(@ApplicationContext context: Context): AppPreferences {

    companion object {
        const val THEME = "theme"
    }

    private var preference = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
    private var editor = preference.edit()

    override var theme: String
        get() = getString(THEME)
        set(value) {
            saveString(THEME, value)
        }

    private fun saveString(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    private fun getString(key: String, defaultValue: String = ""): String {
        return preference.getString(key, defaultValue) ?: defaultValue
    }
}