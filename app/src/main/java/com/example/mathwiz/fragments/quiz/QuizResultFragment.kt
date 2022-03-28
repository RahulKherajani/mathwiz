/*
* This file contains the backend logic for Quiz Results.
* */

package com.example.mathwiz.fragments.quiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mathwiz.*
import com.example.mathwiz.databinding.FragmentQuizResultBinding
import com.google.android.material.snackbar.Snackbar

class QuizResultFragment : Fragment() {
    private var _binding: FragmentQuizResultBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentQuizResultBinding.inflate(inflater, container, false)

        // Extracting values from the Quiz Fragment
        val userId = MathWiz.userData?.id
        val categoryName = arguments?.getString("categoryName")
        var correct = arguments?.getInt("correct")
        var total = arguments?.getInt("total")

        // Displaying Score to the user
        binding.Score.text = "You scored $correct out of $total"
        activity?.title = "Result"

        // Storing the score to Firestore
        if(userId?.isNotBlank() == true) {
            fstore.collection("users").document(userId.toString()).collection("statistics")
                .document(categoryName.toString()).get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val document = task.result
                        if (document != null) {

                            correct = correct?.plus(document.getLong("correct")!!.toInt())
                            total = total?.plus(document.getLong("total")!!.toInt())

                            fstore.collection("users").document(userId.toString())
                                .collection("statistics")
                                .document(categoryName.toString())
                                .update("correct", correct, "total", total)
                                .addOnCompleteListener { task1 ->
                                    if (task1.isSuccessful) {
                                        Log.d("TAG", "Statistics Updated")
                                    } else {
                                        Log.d("TAG", "Error ", task1.exception)
                                    }
                                }
                        } else {
                            Log.d("TAG", "No such document")
                        }
                    }
                }
        }


        // Navigates to Categories Page on onClick
        binding.goToCategoriesPageButton.setOnClickListener {
            val myIntent = Intent(this.context, HomeActivity::class.java)
            startActivity(myIntent)
            activity?.finish()
        }

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = MathWiz.userData?.id
        if(userId.isNullOrBlank()){
            Snackbar.make(binding.root, "Please Signup to back up your Statistics", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}