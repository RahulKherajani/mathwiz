package com.example.mathwiz.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mathwiz.LoginActivity
import com.example.mathwiz.MathWiz
import com.example.mathwiz.R
import com.example.mathwiz.auth
import com.example.mathwiz.databinding.FragmentProfileBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import java.io.*
import java.lang.Exception

class ProfileFragment : Fragment(), View.OnClickListener {

    private var _binding : FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var inEditing : Boolean = false

    private val PROFILE_FILE_NAME : String = "profile"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val editButton :Button = _binding!!.root.findViewById(R.id.edit_button)
        val signOrLogoutButton :Button = _binding!!.root.findViewById(R.id.sign_or_logout_button)
        editButton.setOnClickListener(this)
        signOrLogoutButton.setOnClickListener(this)
        // init profile
        initProfileData()
        return _binding!!.root
    }


    override fun onClick(v: View?) {

        val layout : ConstraintLayout? = activity?.findViewById(R.id.container)
        when (v?.id) {
            R.id.edit_button -> {

                //Verify that the email is in the correct format
                var email = _binding!!.profileEmail.text.toString()
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Snackbar.make(v, "Invalid email address. Please re-enter.", BaseTransientBottomBar.LENGTH_SHORT).show()
                } else {

                    //_binding!!.profileEmail.isEnabled = !_binding!!.profileEmail.isEnabled
                    _binding!!.profileName.isEnabled = !_binding!!.profileName.isEnabled
                    _binding!!.profileGrade.setEnabled(!_binding!!.profileGrade.isEnabled)

                    if(inEditing){
                        saveProfile(_binding!!.profileName.text.toString(),_binding!!.profileGrade.selectedItem.toString(), _binding!!.profileEmail.text.toString())
                        _binding!!.editButton.setText("Edit Profile")

                        if(MathWiz.userData?.name == ""){
                            _binding!!.signOrLogoutButton.setText("Sign Up")
                        }else{
                            _binding!!.signOrLogoutButton.setText("Logout")
                        }

                    }else{
                        Log.e("ProfileFragment", "Edit Profile")
                        _binding!!.editButton.setText("Save")
                        _binding!!.signOrLogoutButton.setText("Cancel")
                    }

                    // finally, change not in editing
                    inEditing = !inEditing
                }
            }
            R.id.sign_or_logout_button ->{
                // todo navigate to sign up or logout
                if(inEditing){
                    _binding!!.signOrLogoutButton.setText("Cancel")
                }else{
                    Log.e("ProfileFragment","Sign Up or Logout")

                    clearUserInfo()

                    if(MathWiz.userData?.email != ""){
                        auth.signOut()
                    }

                    // goto new user page
                    activity?.let {
                        val intent = Intent(it, LoginActivity::class.java)
                        it.startActivity(intent)
                    }
                }
            }
        }
    }

    private fun clearUserInfo() {
        MathWiz.userData?.name = ""
        MathWiz.userData?.grade = ""
        MathWiz.userData?.email = ""
    }

    private fun saveProfile(name :String, grade : String, email : String?){
        MathWiz.userData?.name = name
        MathWiz.userData?.grade = grade
        if(email != ""){
            MathWiz.userData?.email = email
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    /**
     * init profile data from file
     */
    private fun initProfileData(){
        _binding!!.profileGrade.setEnabled(false)
        _binding!!.profileName.setText(MathWiz.userData?.name)
        _binding!!.profileGrade.setSelection(getIndexOfGrade(MathWiz.userData?.grade))
        if(MathWiz.userData?.email != ""){
            _binding!!.profileEmail.setText(MathWiz.userData?.email)
        }
        // already login
        if (MathWiz.userData?.email != ""){
            _binding!!.signOrLogoutButton.setText("Logout")
        }
        inEditing = false

    }

    private fun getIndexOfGrade(grade : String?) : Int {
        if ("Grade Three".equals(grade,true)){
            return 0;
        }else if("Grade Four".equals(grade, true)){
            return 1;
        }
        return 2;
    }
    
}

