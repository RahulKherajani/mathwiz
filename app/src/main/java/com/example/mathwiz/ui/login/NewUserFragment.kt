package com.example.mathwiz.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mathwiz.HomeActivity
import com.example.mathwiz.MathWiz
import com.example.mathwiz.R
import com.example.mathwiz.databinding.FragmentNewUserBinding

class NewUserFragment : Fragment() {
    private var _binding: FragmentNewUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //If personal details have already been entered, proceed to the login page
        if(MathWiz.userData?.name != ""){
            val intent = Intent(this.context, HomeActivity::class.java).apply {
            }
            startActivity(intent)
        }

        binding.newUserButton.setOnClickListener {
            findNavController().navigate(R.id.action_NewUserFragment_to_EnterDetailsFragment)
        }
        binding.returningUserButton.setOnClickListener {
            findNavController().navigate(R.id.action_NewUserFragment_to_LoginFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}