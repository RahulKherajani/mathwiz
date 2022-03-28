/*
* This file contains the model for Reminders.
*/

package com.example.mathwiz.fragments.reminders

data class ReminderModel(var hour: Int, var min: Int, var ampm: String) {
    var status: Int = 0
        get() = field
        set
}



