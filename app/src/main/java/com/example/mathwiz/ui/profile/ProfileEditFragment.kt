package com.example.mathwiz.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.mathwiz.R

class ProfileEditFragment : Fragment(), View.OnClickListener {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.fragment_profile_edit, container, false)
        val saveButton : Button = view.findViewById(R.id.save_button)
        val cancelButton : Button = view.findViewById(R.id.cancel_button)
        saveButton.setOnClickListener(this)
        cancelButton.setOnClickListener(this)
        return view
    }



    override fun onClick(v: View?) {
        val layout : ConstraintLayout? = activity?.findViewById(R.id.container)
        when (v?.id) {
            R.id.save_button -> {
                Log.e("ProfileFragment", "save")

                // todo save data
                layout?.id?.let {
                    activity?.supportFragmentManager
                        ?.beginTransaction()
                        ?.replace(it,ProfileFragment())
                        ?.commit()
                }
            }
            R.id.cancel_button ->{
                Log.e("ProfileFragment","cancel")
                layout?.id?.let {
                    activity?.supportFragmentManager
                        ?.beginTransaction()
                        ?.replace(it,ProfileFragment())
                        ?.commit()
                }
            }
            else -> {
            }
        }
    }
}