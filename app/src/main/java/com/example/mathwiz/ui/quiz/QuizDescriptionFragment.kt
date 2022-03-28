/*
* This file contains the backend logic for Quiz Description.
* */

package com.example.mathwiz.ui.quiz

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mathwiz.HomeActivity
import com.example.mathwiz.R
import com.example.mathwiz.databinding.FragmentQuizDescriptionBinding

class QuizDescriptionFragment : Fragment() {

    private var _binding: FragmentQuizDescriptionBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentQuizDescriptionBinding.inflate(inflater, container, false)

        // Storing category and its description
        val categoryName = activity?.intent?.extras?.getString("categoryName")
        val categoryDescription = activity?.intent?.extras?.getString("categoryDescription")
        val description =
            "Category Name: $categoryName \n Category Description: $categoryDescription"
        binding.descriptionBox.text = description

        // Setting Title of appbar
        activity?.title = categoryName

        // onClick Listener for Exit Button
        binding.exitQuizButton.setOnClickListener {
            val myIntent = Intent(this.context, HomeActivity::class.java)
            startActivity(myIntent)
            activity?.finish()
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryName = activity?.intent?.extras?.getString("categoryName")

        // onClick Listener for Start Button
        binding.startQuizButton.setOnClickListener {
            val bundle = bundleOf("categoryName" to categoryName)
            findNavController().navigate(
                R.id.action_QuizDescriptionFragment_to_QuizQuestionFragment,
                bundle
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}