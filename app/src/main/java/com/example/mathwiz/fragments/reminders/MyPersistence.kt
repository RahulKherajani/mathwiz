/*
* This file contains the persistence logic for Reminders.
*/

package com.example.mathwiz.fragments.reminders

object MyPersistence {

    val reminders = ArrayList<ReminderModel>()

    init {
        initializeNotesPersistence();
    }

    private fun initializeNotesPersistence() {
    }
}