package com.example.mathwiz.ui.categories

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mathwiz.MathWiz
import com.example.mathwiz.databinding.FragmentCategoriesBinding
import com.example.mathwiz.fstore


class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var recyclerView: RecyclerView? = null
    private var category: ArrayList<CategoryModel>? = null
    private var filteredCategory: ArrayList<CategoryModel>? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var categoryAdapter: CategoryAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val categoriesViewModel =
            ViewModelProvider(this).get(CategoriesViewModel::class.java)

        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        categoriesViewModel.text.observe(viewLifecycleOwner) {
        }

        recyclerView = binding.recyclerViewItem
        gridLayoutManager =
            GridLayoutManager(activity, 3, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)

        category = ArrayList()
        category = setCategories()
        filteredCategory = setCategories()
        categoryAdapter = activity?.let { CategoryAdapter(it, filteredCategory!!) }
        recyclerView?.adapter = categoryAdapter

        binding.btnSearch.setOnClickListener {
            filteredCategory?.clear()
            val searchText = binding.etSearchCategory.text.toString().lowercase()
            if(searchText.isNotBlank()){
                category?.forEach {
                    if(it.categoryName.lowercase().contains(searchText)){
                        filteredCategory?.add(it)
                    }
                }
                recyclerView?.adapter?.notifyDataSetChanged()
            } else {
                filteredCategory?.clear()
                category?.let { filteredCategory?.addAll(it) }
                recyclerView?.adapter?.notifyDataSetChanged()
            }
        }
        return root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setCategories(): ArrayList<CategoryModel> {
        val arrayList: ArrayList<CategoryModel> = ArrayList()
        val grade = MathWiz.userData?.grade
        fstore.collection("categories").document(grade.toString()).get().addOnCompleteListener{ task ->
            if (task.isSuccessful) {
            val document = task.result
            if (document != null) {
                val count = document.getLong("count")
                for (i in 1..count!!){
                    val category = document["category$i"] as List<*>
                    arrayList.add(CategoryModel(category[0] as String, category[1] as String))
                    recyclerView?.adapter?.notifyDataSetChanged()
                }
            } else {
                Log.d("TAG", "No such document")
            }
        } else {
            Log.d("TAG", "Error ", task.exception)
        }}
        return arrayList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}