package com.example.mathwiz.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mathwiz.R
import com.example.mathwiz.databinding.FragmentEnterDetailsBinding
import com.example.mathwiz.databinding.FragmentSignupBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class EnterDetailsFragment : Fragment() {
    private var _binding: FragmentEnterDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEnterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveDetailsButton.setOnClickListener {
            findNavController().navigate(R.id.action_EnterDetailsFragment_to_WantSignupFragment)
            Snackbar.make(view, "Details saved!", BaseTransientBottomBar.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}