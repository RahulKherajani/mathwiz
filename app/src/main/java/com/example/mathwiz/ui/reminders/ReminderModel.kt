package com.example.mathwiz.ui.reminders

data class ReminderModel(var hour: Int, var min: Int, var ampm: String) {
    var status: Int = 0
        get() = field
        set
}



