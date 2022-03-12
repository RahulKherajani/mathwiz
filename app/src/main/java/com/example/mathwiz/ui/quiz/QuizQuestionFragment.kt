package com.example.mathwiz.ui.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mathwiz.R
import com.example.mathwiz.databinding.FragmentQuizQuestionBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class QuizQuestionFragment : Fragment() {

    private var _binding: FragmentQuizQuestionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentQuizQuestionBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitQuestionButton.setOnClickListener {
            findNavController().navigate(R.id.action_QuizQuestionFragment_to_QuizResultFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}