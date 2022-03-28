package com.example.mathwiz.ui.reminders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mathwiz.R
import com.example.mathwiz.ui.reminders.MyPersistence
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RemindersFragment : Fragment(), AdapterView.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val notesListViewUI: ListView = view.findViewById(R.id.list_item)
        notesListViewUI.adapter = ReminderAdapter(
            view.context,
            MyPersistence.reminders
        )
        notesListViewUI.onItemClickListener = this

        view.findViewById<FloatingActionButton>(R.id.floating_btn_reminder).setOnClickListener {
            findNavController().navigate(R.id.action_navigation_reminders_to_addReminderFragment)
        }

    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val b = Bundle()
        b.putInt("position", p2)
        findNavController().navigate(R.id.action_navigation_reminders_to_editReminderFragment, b)
    }
}