package com;

import android.app.Application;
import android.content.Context
import persistence.entitys.MyAppRoomDatabase
import persistence.sharedPreferences.MySharedPreferences

class MyApplication: Application() {

    companion object {
        lateinit var preferences: MySharedPreferences
        lateinit var myApplicationContext: Context
        lateinit var myAppDatabase: MyAppRoomDatabase
    }
    override fun onCreate() {
        super.onCreate()
        preferences = MySharedPreferences(applicationContext)
        myApplicationContext = applicationContext
        myAppDatabase = MyAppRoomDatabase.roomDatabase
    }
}