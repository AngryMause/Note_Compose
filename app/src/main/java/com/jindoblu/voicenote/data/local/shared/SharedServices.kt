package com.jindoblu.voicenote.data.local.shared

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SharedServices @Inject constructor(
private val context: Context
) {
    private val sharedPref = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    private val editer = sharedPref.edit()


    fun saveString(value: String) {
        editer.putString(KEY, value)
        editer.apply()
    }

    fun getString(): String? {
        return sharedPref.getString(KEY, null)
    }

    companion object {
        private const val KEY = "key"
    }


}