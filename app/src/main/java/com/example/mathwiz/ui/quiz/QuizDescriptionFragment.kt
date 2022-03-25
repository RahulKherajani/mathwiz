package com.example.mathwiz.ui.quiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mathwiz.R
import com.example.mathwiz.databinding.FragmentQuizDescriptionBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class QuizDescriptionFragment : Fragment() {

    private var _binding: FragmentQuizDescriptionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentQuizDescriptionBinding.inflate(inflater, container, false)
        val categoryName = activity?.intent?.extras?.getString("categoryName")
        val categoryDescription = activity?.intent?.extras?.getString("categoryDescription")
        Log.i("TAG", categoryName.toString())
        Log.i("TAG", categoryDescription.toString())
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startQuizButton.setOnClickListener {
            findNavController().navigate(R.id.action_QuizDescriptionFragment_to_QuizQuestionFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}