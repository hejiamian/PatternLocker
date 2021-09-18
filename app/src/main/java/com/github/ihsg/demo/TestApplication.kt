package com.github.ihsg.demo

import android.app.Application
import android.content.Context
import com.github.ihsg.demo.util.SharedPreferencesUtil

/**
 * Created by hsg on 14/10/2017.
 */
class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferencesUtil.init(this)
    }

}