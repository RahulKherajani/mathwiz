package com.example.mathwiz.ui.reminders

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.mathwiz.R
import com.example.mathwiz.ui.reminders.ReminderModel
import com.example.mathwiz.ui.reminders.MyPersistence

class AddReminderFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_reminder, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.button5).setOnClickListener {
            var timePicker = view.findViewById<TimePicker>(R.id.time_picker)
            MyPersistence.reminders.add(
                ReminderModel(
                    timePicker.hour,
                    timePicker.minute,
                    ""
                )
            )
            findNavController().popBackStack()
        }
        view.findViewById<Button>(R.id.addReminderBack).setOnClickListener {
            findNavController().popBackStack()
        }
    }
}