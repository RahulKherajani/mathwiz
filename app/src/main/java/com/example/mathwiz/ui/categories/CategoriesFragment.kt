package com.example.mathwiz.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mathwiz.adapters.categories.CategoryAdapter
import com.example.mathwiz.databinding.FragmentCategoriesBinding
import com.example.mathwiz.models.categories.CategoryModel

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var recyclerView: RecyclerView? = null
    private var category: ArrayList<CategoryModel>? = null
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
        categoryAdapter = activity?.let { CategoryAdapter(it, category!!) }
        recyclerView?.adapter = categoryAdapter

        return root
    }

    private fun setCategories(): ArrayList<CategoryModel> {

        var arrayList: ArrayList<CategoryModel> = ArrayList()

        arrayList.add(CategoryModel("Category A"))
        arrayList.add(CategoryModel( "Category B"))
        arrayList.add(CategoryModel( "Category C"))
        arrayList.add(CategoryModel( "Category D"))
        arrayList.add(CategoryModel( "Category E"))
        arrayList.add(CategoryModel( "Category G"))
        arrayList.add(CategoryModel( "Category H"))
        arrayList.add(CategoryModel( "Category I"))
        arrayList.add(CategoryModel( "Category J"))
        arrayList.add(CategoryModel( "Category K"))
        arrayList.add(CategoryModel( "Category L"))
        arrayList.add(CategoryModel( "Category M"))
        arrayList.add(CategoryModel( "Category N"))


        return arrayList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}