package java.com.interpretationofdreams.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPreferencesHelper(var context: Context) {

    private val APP_PREFS_NAME = "APP_PREFERENCE"

    private var preferences: SharedPreferences

    private val mContext: Context = context


    init {
        preferences = mContext.getSharedPreferences(APP_PREFS_NAME, MODE_PRIVATE)
    }

}