package com.example.mathwiz.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mathwiz.*
import com.example.mathwiz.databinding.FragmentSignupBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.WriteBatch

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

            val email = binding.etSignupEmail.text.toString()
            val password = binding.etNewPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            //If any of the fields are empty
            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(confirmPassword) || TextUtils.isEmpty(password)){
                Snackbar.make(view, "Please fill all fields before continuing.", BaseTransientBottomBar.LENGTH_SHORT).show()
            }
            //If the email is invalid
            else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Snackbar.make(view, "Invalid email address. Please re-enter.", BaseTransientBottomBar.LENGTH_SHORT).show()
            }
            else if(password.length < 6){
                Snackbar.make(view, "Password must be greater than 6 characters.", BaseTransientBottomBar.LENGTH_SHORT).show()
            }
            //If the passwords do not match
            else if(password != confirmPassword){
                Snackbar.make(view, "Passwords do not match. Please re-enter.", BaseTransientBottomBar.LENGTH_SHORT).show()
            } else {

                //Attempt sign up
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        //Save User Data Locally
                        MathWiz.userData?.email = email
                        val name = MathWiz.userData?.name
                        val grade = MathWiz.userData?.grade

                        //Create user entry in database
                        val userID = auth.currentUser?.uid
                        MathWiz.userData?.id =userID
                            if (userID != null) {
                            val documentReference : DocumentReference = fstore.collection("users").document(userID)
                            val user : HashMap<String, String> = HashMap()
                            user["email"] = email
                            user["name"] = name.toString()
                            user["grade"] = grade.toString()
                            documentReference.set(user).addOnCompleteListener { task2 ->
                                if (task2.isSuccessful) {
                                    val statisticsCollection: CollectionReference = documentReference.collection("statistics")
                                    val map : HashMap<String, Int> = HashMap()
                                    map["correct"] = 0
                                    map["total"]= 0
                                    fstore.runBatch { batch ->
                                        for (i in 3..5){
                                            val additionDocument: DocumentReference = statisticsCollection.document("Addition - $i")
                                            batch.set(additionDocument, map)
                                            val subtractionDocument: DocumentReference = statisticsCollection.document("Subtraction - $i")
                                            batch.set(subtractionDocument, map)
                                            val multiplicationDocument: DocumentReference = statisticsCollection.document("Multiplication - $i")
                                            batch.set(multiplicationDocument, map)
                                            val divisionDocument: DocumentReference = statisticsCollection.document("Division - $i")
                                            batch.set(divisionDocument, map)
                                        }
                                    }.addOnCompleteListener {
                                            Log.e("SignupFragment","User entry created")
                                    }
                                }
                            }
                        }

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
                }.addOnFailureListener { exception ->
                    Snackbar.make(view,exception.localizedMessage,BaseTransientBottomBar.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}