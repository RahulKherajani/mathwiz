/*
* This file contains the data ViewModel for Profile
*/
package com.example.mathwiz.fragments.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private val _editButtonText = MutableLiveData<String>().apply {
        value = "Edit Profile"
    }
    /**
     * edit button
     */
    val editButtonText : LiveData<String> = _editButtonText;


    private val _signOrLogoutButtonText = MutableLiveData<String>().apply {
        value = "Sign Up"
    }
    /**
     * sign up or logout button
     */
    val signOrLogoutButtonText : LiveData<String> = _signOrLogoutButtonText;


    private val _profileName = MutableLiveData<String>().apply {
        value = ""
    }

    /**
     * profile name
     */
    val profileName : LiveData<String> = _profileName;


    private val _grade = MutableLiveData<String>().apply {
        value = "96"
    }
    /**
     * grade
     */
    val grade : LiveData<String> = _grade;

    private val _email = MutableLiveData<String>().apply {
        value = "sample@gmail.com"
    }

    /**
     * sign up or logout button
     */
    val email : LiveData<String> = _email;
}