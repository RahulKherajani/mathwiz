package com.example.mathwiz.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mathwiz.HomeActivity
import com.example.mathwiz.R
import com.example.mathwiz.databinding.FragmentNewUserBinding
import com.example.mathwiz.databinding.FragmentWantSignupBinding

class WantSignupFragment : Fragment() {
    private var _binding: FragmentWantSignupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWantSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.yesButton.setOnClickListener {
            findNavController().navigate(R.id.action_WantSignup_to_SignupFragment)
        }
        binding.noButton.setOnClickListener {
            val intent = Intent(this.context, HomeActivity::class.java).apply {
            }
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}