package com.example.mathwiz

import android.app.Application

/*
    This class allows for global access of preferences
 */

val USER_DATA: UserData by lazy {
    MathWiz.userData!!
}

class MathWiz: Application()
{
    companion object {
        var userData: UserData? = null
        lateinit var instance: MathWiz
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        userData = UserData(applicationContext)
    }
}