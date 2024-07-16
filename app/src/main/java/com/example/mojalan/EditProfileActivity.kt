package com.example.mojalan

import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.mojalan.databinding.EditProfilBinding

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: EditProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up any necessary logic or event listeners
        binding.buttonBack.setOnClickListener {
            onBackPressed()
        }

        binding.buttonSave.setOnClickListener {
            // Handle save button click
        }

        binding.showPassword1.setOnClickListener {
            // Handle show/hide password for the first password field
            togglePasswordVisibility(binding.editTextPassword)
        }

        binding.showPassword2.setOnClickListener {
            // Handle show/hide password for the second password field
            togglePasswordVisibility(binding.editTextConfirmPassword)
        }
    }

    private fun togglePasswordVisibility(editText: EditText) {
        if (editText.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        } else {
            editText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }
        editText.setSelection(editText.text.length)
    }
}
