/*
* This file contains the backend logic for Statistics Fragment.
*/

package com.example.mathwiz.fragments.statistics

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.mathwiz.MathWiz
import com.example.mathwiz.databinding.FragmentStatisticsBinding
import com.example.mathwiz.fstore
import com.google.android.material.snackbar.Snackbar


class StatisticsFragment : Fragment() {

    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val categoryNames: ArrayList<String> = ArrayList()
        val userId = MathWiz.userData?.id

        // Statistics work only if user is registered i.e. userId not blank
        if(userId?.isNotBlank() == true) {
            val grade = MathWiz.userData?.grade
            val spinner = binding.categoriesSpinner
            val pieChart = binding.statsProgressbar
            val pieChartText = binding.pieChartText

            //Retrieve Categories for User's Grade & set in Spinner
            fstore.collection("categories").document(grade.toString()).get().addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null) {
                        val count = document.getLong("count")
                        for (i in 1..count!!){
                            val category = document["category$i"] as List<*>
                            categoryNames.add(category[0] as String)
                        }
                        spinner.adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.simple_spinner_item, categoryNames) } as SpinnerAdapter
                    } else {
                        Log.d("TAG", "No such document")
                    }
                } else {
                    Log.d("TAG", "Error ", task.exception)
                }}


            //onItemSelectedListener on Spinner which fetches User's Statistics for a particular category
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //fetch statistics from Firestore
                    fstore.collection("users").document(userId.toString()).collection("statistics")
                        .document(categoryNames[position]).get().addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val document = task.result
                                if (document != null) {
                                    val correct = document.getDouble("correct")
                                    val total = document.getDouble("total")
                                    if (total != 0.0) {
                                        pieChart.progress = ((correct!! / total!!) * 100).toInt()
                                    } else {
                                        pieChart.progress = 0
                                    }
                                    pieChartText.text = "${correct?.toInt()} / ${total.toInt()}"
                                } else {
                                    Log.d("TAG", "No such document")
                                }
                            } else {
                                Log.d("TAG", "Error ", task.exception)
                            }
                        }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Display a SnackBar if user is not registered i.e userId id null
        val userId = MathWiz.userData?.id
        if(userId.isNullOrBlank()){
            Snackbar.make(binding.constraintLayout, "Please SignUp to view Statistics", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}