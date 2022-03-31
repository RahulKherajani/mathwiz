/*
* This file contains the backend logic for Enter Details.
* */
package com.example.mathwiz.fragments.login

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
import com.example.mathwiz.databinding.FragmentEnterDetailsBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class EnterDetailsFragment : Fragment() {
    private var _binding: FragmentEnterDetailsBinding? = null
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

            var name = binding.etName.text.toString()

            if(name == ""){
                Snackbar.make(view, "Please fill all fields before continuing.", BaseTransientBottomBar.LENGTH_SHORT).show()
            } else {
                //Save the name and grade to the preferences file
                MathWiz.userData?.name = name
                MathWiz.userData?.grade = binding.gradeSpinner.selectedItem.toString()

                Snackbar.make(view, "Details saved!", BaseTransientBottomBar.LENGTH_SHORT).show()
                if(MathWiz.userData?.email == ""){
                    //Proceed to 'Want to Signup?' page
                    findNavController().navigate(R.id.action_EnterDetailsFragment_to_WantSignupFragment)
                } else {
                    val intent = Intent(this.context, HomeActivity::class.java).apply {
                    }
                    startActivity(intent)
                    activity?.finish()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}