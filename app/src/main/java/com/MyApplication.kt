package com;

import android.app.Application;
import persistence.sharedPreferences.MySharedPreferences

class MyApplication: Application() {

    companion object {
        lateinit var preferences: MySharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        preferences = MySharedPreferences(applicationContext)
    }
}