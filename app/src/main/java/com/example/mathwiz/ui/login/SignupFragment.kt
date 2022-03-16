package com.example.mathwiz.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mathwiz.HomeActivity
import com.example.mathwiz.MathWiz
import com.example.mathwiz.R
import com.example.mathwiz.databinding.FragmentSignupBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.savePasswordButton.setOnClickListener {

            var email = binding.etSignupEmail.text.toString()
            var password = binding.etNewPassword.text.toString()
            var confirmPassword = binding.etConfirmPassword.text.toString()

            //If any of the fields are empty
            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(confirmPassword) || TextUtils.isEmpty(password)){
                Snackbar.make(view, "Please fill all fields before continuing.", BaseTransientBottomBar.LENGTH_SHORT).show()
            }
            //If the email is invalid
            else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Snackbar.make(view, "Invalid email address. Please re-enter.", BaseTransientBottomBar.LENGTH_SHORT).show()
            }
            //If the passwords do not match
            else if(!password.equals(confirmPassword)){
                Snackbar.make(view, "Passwords do not match. Please re-enter.", BaseTransientBottomBar.LENGTH_SHORT).show()
            } else {

                //TODO - save to cloud

                //Save the email locally
                MathWiz.userData?.email = email

                Snackbar.make(view, "Sign up successful!", BaseTransientBottomBar.LENGTH_SHORT).show()
                if(MathWiz.userData?.name == ""){
                    findNavController().navigate(R.id.action_SignupFragment_to_EnterDetailsFragment)
                } else {
                    //Proceed to Home
                    val intent = Intent(this.context, HomeActivity::class.java).apply {
                    }
                    startActivity(intent)
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}