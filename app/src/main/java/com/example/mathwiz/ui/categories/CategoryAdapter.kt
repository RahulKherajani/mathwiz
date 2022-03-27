package com.example.mathwiz.ui.categories

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mathwiz.QuizActivity
import com.example.mathwiz.R

class CategoryAdapter(var context: Context, private var arrayList: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.category, parent, false)
        return ItemHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        val category: CategoryModel = arrayList[position]

        holder.categoryName.text = category.categoryName

        holder.cardCategory.setOnClickListener {
            val intent = Intent(context, QuizActivity::class.java)
            intent.putExtra("categoryName", category.categoryName)
            intent.putExtra("categoryDescription", category.categoryDescription)
            context.startActivity(intent)
        }

    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var categoryName: TextView = itemView.findViewById<TextView>(R.id.title_text_view)
        var cardCategory = itemView.findViewById<CardView>(R.id.cardCategory)
    }
}