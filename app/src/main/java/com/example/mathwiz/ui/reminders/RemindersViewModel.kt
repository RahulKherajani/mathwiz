package com.example.mathwiz.ui.reminders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RemindersViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Reminders Fragment"
    }
    val text: LiveData<String> = _text
}