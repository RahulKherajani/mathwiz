/*
* This file contains the backend logic for Change Password.
*/
package com.example.mathwiz.fragments.login

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mathwiz.R
import com.example.mathwiz.auth
import com.example.mathwiz.databinding.FragmentChangePasswordBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class ChangePasswordFragment : Fragment() {

    private var _binding: FragmentChangePasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.savePasswordButton.setOnClickListener {

            val email = binding.etChangePasswordEmail.text.toString()

            //If any of the fields are empty
            if(TextUtils.isEmpty(email)){
                Snackbar.make(view, "Please fill all fields before continuing.", BaseTransientBottomBar.LENGTH_SHORT).show()
            }
            else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Snackbar.make(view, "Invalid email address. Please re-enter.", BaseTransientBottomBar.LENGTH_SHORT).show()
            }
            //Otherwise, success
            else {

                //Send password reset email
                auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Snackbar.make(view, "Password reset email sent.", BaseTransientBottomBar.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener { exception ->
                    Snackbar.make(view, exception.localizedMessage, BaseTransientBottomBar.LENGTH_SHORT).show()
                }
            }
        }

        binding.cancelPasswordButton.setOnClickListener {
            findNavController().navigate(R.id.action_ChangePasswordFragment_to_LoginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}