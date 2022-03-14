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
import com.example.mathwiz.LoginActivity
import com.example.mathwiz.R
import com.example.mathwiz.databinding.FragmentProfileBinding
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
                _binding!!.profileEmail.isEnabled = !_binding!!.profileEmail.isEnabled
                _binding!!.profileName.isEnabled = !_binding!!.profileName.isEnabled
                _binding!!.profileGrade.isEnabled = !_binding!!.profileGrade.isEnabled
                if(inEditing){
                    _binding!!.editButton.setText("Edit Profile")
                }else{
                    Log.e("ProfileFragment", "Edit Profile")
                    _binding!!.editButton.setText("Save")
                }
                // finally, change not in editing
                inEditing = !inEditing
            }
            R.id.sign_or_logout_button ->{
                // todo navigate to sign up or logout
                if(inEditing){
                    _binding!!.signOrLogoutButton.setText("Cancel")
                }else{
                    Log.e("ProfileFragment","Sign Up or Logout")

                    val login : Boolean = true
                    if (login) {
                        Log.e("ProfileFragment","Have Login")
                        activity?.let {
                            val intent = Intent(it,LoginActivity::class.java)
                            it.startActivity(intent)
                        }
                    }else{
                        // todo change login to sign up
                        activity?.let {
                            val intent = Intent(it,LoginActivity::class.java)
                            it.startActivity(intent)
                        }
                    }
                }
            }
            else -> {
            }
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
        _binding!!.profileName.setText("Raft")
        _binding!!.profileGrade.setText("90")
        _binding!!.profileEmail.setText("sample@gmail.com")
    }


    /**
     * first line: username
     * second line:  grade
     * third line: email
     */
    private fun readProfileDataFromFile() {
        var reader : FileReader? = null
        try{
            reader = FileReader(PROFILE_FILE_NAME)
            var lines = reader.readLines()
            if(lines.count() == 3){
                _binding!!.profileName.setText(lines[0])
                _binding!!.profileGrade.setText(lines[1])
                _binding!!.profileEmail.setText(lines[2])
            }
        }catch (e :Exception){
            Log.e("ProfileFragment",e.stackTraceToString())
        }finally {
            reader?.close()
        }
    }

    /**
     * write profile data into internal file
     * first line: username
     * second line:  grade
     * third line: email
     */
    private fun writeProfileDataToFile(name :String,grade : String, email : String){
        var writer : FileWriter? = null
        try{
            writer = FileWriter(PROFILE_FILE_NAME,false)
            writer.write(name + "\n");
            writer.write(grade + "\n");
            writer.write(email + "\n");
        }catch (e :Exception){
            Log.e("ProfileFragment",e.stackTraceToString())
        }finally {
            writer?.close()
        }
    }


}

class Profile{
    var name : String
    var grade : Int
    var email : String

    constructor(name: String, grade: Int, email: String) {
        this.name = name
        this.grade = grade
        this.email = email
    }
}