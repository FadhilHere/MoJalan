package com.example.mojalan

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mojalan.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.loginText.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.registerButton.setOnClickListener {
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()
            val confirmPass = binding.Confirmpassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty())
            {
                if (pass == (confirmPass)) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Empty Fields Are not Allowed", Toast.LENGTH_SHORT).show()
            }
        }

    }
}