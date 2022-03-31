/* LoginActivity with No Action Bar
and 6 Fragments: ChangePassword, EnterDetails, Login, NewUser, Signup, WantSignup */
package com.example.mathwiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.mathwiz.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findNavController(R.id.nav_host_login_activity)
    }
}