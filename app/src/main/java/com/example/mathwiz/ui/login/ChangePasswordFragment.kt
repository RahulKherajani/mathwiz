package com.example.mathwiz.ui.login

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mathwiz.R
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

        //TODO send security code
        val securityCode = "1111"

        binding.savePasswordButton.setOnClickListener {

            val securityCodeInput = binding.etSecurityCode.text.toString()
            val newPassword = binding.etNewPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            //If any of the fields are empty
            if(TextUtils.isEmpty(securityCodeInput) || TextUtils.isEmpty(confirmPassword) || TextUtils.isEmpty(newPassword)){
                Snackbar.make(view, "Please fill all fields before continuing.", BaseTransientBottomBar.LENGTH_SHORT).show()
            }
            //If the passwords do not match
            else if(!newPassword.equals(confirmPassword)){
                Snackbar.make(view, "Passwords do not match. Please re-enter your password.", BaseTransientBottomBar.LENGTH_SHORT).show()
            }
            //If the security code does not match
            else if (!securityCode.equals(securityCodeInput)){
                Snackbar.make(view, "Security code does not match. Please re-enter the code.", BaseTransientBottomBar.LENGTH_SHORT).show()
            }
            //Otherwise, success
            else {

                //TODO - change password

                findNavController().navigate(R.id.action_ChangePasswordFragment_to_LoginFragment)
                //Password change success message
                Snackbar.make(view, "Password change successful!", BaseTransientBottomBar.LENGTH_SHORT).show()
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