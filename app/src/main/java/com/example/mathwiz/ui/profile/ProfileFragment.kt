package com.example.mathwiz.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.mathwiz.R

class ProfileFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        val editButton :Button = view.findViewById(R.id.edit_button)
        val signOrLogoutButton :Button = view.findViewById(R.id.sign_or_logout_button)
        editButton.setOnClickListener(this)
        signOrLogoutButton.setOnClickListener(this)
        return view
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.edit_button -> {
                Log.e("ProfileFragment", "Edit Profile")
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.profile_fragment, ProfileEditFragment())
                    ?.commit()
            }
            R.id.sign_or_logout_button ->{
                // todo navigate to sign up or logout
                Log.e("ProfileFragment","Sign Up or Logout")
            }
            else -> {
            }
        }
    }


}