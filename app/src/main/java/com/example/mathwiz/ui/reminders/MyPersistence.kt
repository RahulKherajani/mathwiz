package com.example.mathwiz.ui.reminders

import com.example.mathwiz.ui.reminders.ReminderModel

object MyPersistence {

    val reminders = ArrayList<ReminderModel>()

    init {
        initializeNotesPersistence();
    }

    private fun initializeNotesPersistence() {
    }
}