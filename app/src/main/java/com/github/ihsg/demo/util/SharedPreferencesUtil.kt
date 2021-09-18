package com.github.ihsg.demo.util

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by hsg on 14/10/2017.
 */
object SharedPreferencesUtil {

    private lateinit var prefer: SharedPreferences

    fun init(applicationContext: Context) {
        prefer = applicationContext.getSharedPreferences("test", Context.MODE_PRIVATE)
    }

    fun saveString(name: String?, data: String?) {
        val edit = prefer.edit()
        edit.putString(name, data).apply()
    }

    fun getString(name: String?): String? {
        return prefer.getString(name, null)
    }
}