package com.example.mathwiz.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mathwiz.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(CategoriesViewModel::class.java)

        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView1: TextView = binding.textCard1
        val textView2: TextView = binding.textCard2
        val textView3: TextView = binding.textCard3
        val textView4: TextView = binding.textCard4
        val textView5: TextView = binding.textCard5
        val textView6: TextView = binding.textCard6

        homeViewModel.text.observe(viewLifecycleOwner) {
            textView1.text = it
            textView2.text = it
            textView3.text = it
            textView4.text = it
            textView5.text = it
            textView6.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}