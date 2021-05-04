package br.com.mesa.newsapp.util

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {
    private val PREFS_NAME = "NEWS_PREFERENCES"
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun save(KEY_NAME: String, text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_NAME, text)
        editor.apply()
    }

    fun getValueString(KEY_NAME: String): String? {
        return sharedPref.getString(KEY_NAME, null)
    }

    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }

    fun removeValue(KEY_NAME: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.remove(KEY_NAME)
        editor.apply()
    }


    companion object {
        const val USER_TOKEN = "USER_TOKEN"
        var preferences: Preferences? = null
        @Synchronized
        fun getPreferencesManagerInstance(context: Context): Preferences {
            if (preferences == null) {
                preferences = Preferences(context)
            }
            return preferences!!
        }
    }
}
