package com.example.mathwiz

import android.app.Application
import com.google.firebase.auth.FirebaseAuth

/*
    This class allows for global access of preferences
 */

lateinit var auth: FirebaseAuth

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
        auth= FirebaseAuth.getInstance()
    }
}