package com.example.mathwiz.adapters.categories

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
import com.example.mathwiz.models.categories.CategoryModel

class CategoryAdapter(var context: Context, var arrayList: ArrayList<CategoryModel>) :
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

        val category: CategoryModel = arrayList.get(position)

        holder.categoryName.text = category.text

        holder.cardCategory.setOnClickListener {
            val intent = Intent(context, QuizActivity::class.java)
            context.startActivity(intent);
        }

    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var categoryName = itemView.findViewById<TextView>(R.id.title_text_view)
        var cardCategory = itemView.findViewById<CardView>(R.id.cardCategory)
    }
}