package com.example.mathwiz.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mathwiz.*
import com.example.mathwiz.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentReference


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {

            var email = binding.etLoginEmail.text.toString()
            var password = binding.etLoginPassword.text.toString()

            //If any of the fields are empty
            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                Snackbar.make(view, "Please fill all fields before continuing.", BaseTransientBottomBar.LENGTH_SHORT).show()
            }
            //If the email is invalid
            else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Snackbar.make(view, "Invalid email address. Please re-enter.", BaseTransientBottomBar.LENGTH_SHORT).show()
            }
            else {

                //Attempt login
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        //Save the email locally
                        MathWiz.userData?.email = email

                        //Retrieve userid
                        val userID = auth.currentUser?.uid
                        MathWiz.userData?.id = userID
                        if(userID != null){
                            val documentReference : DocumentReference = fstore.collection("users").document(userID)
                            documentReference.get().addOnCompleteListener { task ->
                                if(task.isSuccessful){
                                    //Retrieve name, and grade from database
                                    val document = task.result
                                    val name = document.getString("name")
                                    val grade = document.getString("grade")
                                    MathWiz.userData?.name = name
                                    MathWiz.userData?.grade = grade

                                    //Proceed to Home
                                    Snackbar.make(view, "Login successful!", BaseTransientBottomBar.LENGTH_SHORT).show()
                                    val intent = Intent(this.context, HomeActivity::class.java).apply {
                                    }
                                    startActivity(intent)
                                }
                            }.addOnFailureListener { exception ->
                                Snackbar.make(view,exception.localizedMessage,BaseTransientBottomBar.LENGTH_SHORT).show()
                            }
                        }
                    }
                }.addOnFailureListener { exception ->
                    Snackbar.make(view,exception.localizedMessage,BaseTransientBottomBar.LENGTH_SHORT).show()
                }
            }
        }
        binding.forgotPasswordButton.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_ChangePasswordFragment)
        }
        binding.signupButton.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_SignupFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}