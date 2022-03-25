package com.example.mathwiz

import android.content.Context
import android.content.SharedPreferences

/*
    This class stores variables specific to the user/device
 */

class UserData (context: Context)
{
    private val preferencesFileName = "PreferencesFile"
    private val nameName = "name"
    private val gradeName = "grade"
    private val emailName = "email"
    private val userId = "id"

    private val preferences: SharedPreferences = context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE)

    var name: String?
        get() = preferences.getString(nameName, "")
        set(value) = preferences.edit().putString(nameName, value).apply()

    var grade: String?
        get() = preferences.getString(gradeName, "")
        set(value) = preferences.edit().putString(gradeName, value).apply()

    var email: String?
        get() = preferences.getString(emailName, "")
        set(value) = preferences.edit().putString(emailName, value).apply()

    var id: String?
        get() = preferences.getString(userId, "")
        set(value) = preferences.edit().putString(userId, value).apply()
}
