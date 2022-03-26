package com.example.mathwiz.ui.reminders

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.mathwiz.R
import com.example.mathwiz.ui.reminders.ReminderModel

class ReminderAdapter(var context: Context, var arr:ArrayList<ReminderModel>)  : BaseAdapter(),
    AdapterView.OnItemClickListener {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var vH: ViewHolder? = null
        var view: View? = null
        if (convertView == null) {
            view = View.inflate(context, R.layout.reminder_list_view, null);
            vH = ViewHolder();
            vH.textView = view.findViewById(R.id.textView)
            vH.textView2 = view.findViewById(R.id.textView2)
            vH.textView3 = view.findViewById(R.id.textView3)
            view.tag = vH
        } else {
            view = convertView
            vH = view.tag as ViewHolder
        }
        vH.textView?.text = arr.get(position).hour.toString()
        vH.textView2?.text = arr.get(position).min.toString()
        vH.textView3?.text = if(arr.get(position).hour >= 12) "PM" else "AM"

        return view!!
    }

    inner class ViewHolder {
        var textView: TextView? = null
        var textView2: TextView? = null
        var textView3: TextView? = null
    }

    override fun getItem(position: Int): Any {
        return arr.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arr.size
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }
}
