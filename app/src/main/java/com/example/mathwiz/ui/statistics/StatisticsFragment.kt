package com.example.mathwiz.ui.statistics

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mathwiz.databinding.FragmentStatisticsBinding


class StatisticsFragment : Fragment() {

    private var _binding: FragmentStatisticsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val statisticsViewModel =
            ViewModelProvider(this)[StatisticsViewModel::class.java]

        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textStatistics
        statisticsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        //Populate Spinner and add onClickListener
        val categoryNames = arrayOf(
            "Category A",
            "Category B",
            "Category C",
            "Category D",
            "Category E",
            "Category F",
            "Category G"
        )
        val spinner = binding.categoriesSpinner
        spinner?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.simple_spinner_item, categoryNames) } as SpinnerAdapter
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

        // Add a Pie Chart
        val pieChart = binding.statsProgressbar
        val pieChartText = binding.pieChartText
        val correct = 93
        val total = 100
        pieChart.progress =  ((correct.toDouble() / total.toDouble()) * 100).toInt()
        pieChartText.text = "Correct: $correct / Total: $total"

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}