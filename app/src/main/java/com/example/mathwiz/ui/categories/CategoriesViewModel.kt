package com.example.mathwiz.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CategoriesViewModel : ViewModel() {

        private val _text = MutableLiveData<String>().apply {
    }
    val text: LiveData<String> = _text
}