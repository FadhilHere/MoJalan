package com.example.mojalan.Fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.mojalan.R
import com.example.mojalan.TourGuide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

class TambahDataFragment : Fragment() {

    private lateinit var etName: EditText
    private lateinit var etLocation: EditText
    private lateinit var etPrice: EditText
    private lateinit var etDescription: EditText
    private lateinit var etLanguages: EditText
    private lateinit var etTag1: EditText
    private lateinit var etTag2: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnUploadImage: Button
    private lateinit var btnUploadExperience: Button
    private lateinit var database: DatabaseReference
    private lateinit var storageReference: StorageReference
    private var imageUri: Uri? = null
    private var experienceUri: Uri? = null
    private val PICK_IMAGE_REQUEST = 1
    private val PICK_EXPERIENCE_REQUEST = 2
    private val STORAGE_PERMISSION_CODE = 100

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tambah_data, container, false)

        etName = view.findViewById(R.id.etName)
        etLocation = view.findViewById(R.id.etLocation)
        etPrice = view.findViewById(R.id.etPrice)
        etDescription = view.findViewById(R.id.etDescription)
        etLanguages = view.findViewById(R.id.etLanguages)
        etTag1 = view.findViewById(R.id.etTag1)
        etTag2 = view.findViewById(R.id.etTag2)
        btnAdd = view.findViewById(R.id.btnAdd)
        btnUploadImage = view.findViewById(R.id.btnUploadImage)
        btnUploadExperience = view.findViewById(R.id.btnUploadExperience)

        database = FirebaseDatabase.getInstance().reference
        storageReference = FirebaseStorage.getInstance().reference
        auth = FirebaseAuth.getInstance()

        btnUploadImage.setOnClickListener {
            if (checkAndRequestPermissions()) {
                openFileChooser(PICK_IMAGE_REQUEST)
            }
        }

        btnUploadExperience.setOnClickListener {
            if (checkAndRequestPermissions()) {
                openFileChooser(PICK_EXPERIENCE_REQUEST)
            }
        }

        btnAdd.setOnClickListener {
            uploadData()
        }

        return view
    }

    private fun checkAndRequestPermissions(): Boolean {
        val permissionReadStorage = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
        val permissionWriteStorage = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)

        val listPermissionsNeeded = ArrayList<String>()

        if (permissionWriteStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (permissionReadStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(requireActivity(), listPermissionsNeeded.toTypedArray(), STORAGE_PERMISSION_CODE)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            STORAGE_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun openFileChooser(requestCode: Int) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            when (requestCode) {
                PICK_IMAGE_REQUEST -> imageUri = data.data
                PICK_EXPERIENCE_REQUEST -> experienceUri = data.data
            }
        }
    }

    private fun uploadData() {
        val name = etName.text.toString()
        val location = etLocation.text.toString()
        val price = etPrice.text.toString()
        val description = etDescription.text.toString()
        val languages = etLanguages.text.toString()
        val tag1 = etTag1.text.toString()
        val tag2 = etTag2.text.toString()

        if (name.isNotEmpty() && location.isNotEmpty() && price.isNotEmpty() && description.isNotEmpty() && languages.isNotEmpty() && tag1.isNotEmpty() && tag2.isNotEmpty() && imageUri != null && experienceUri != null) {
            val currentUser = auth.currentUser
            val userId = currentUser?.uid

            if (userId != null) {
                val imageRef = storageReference.child("images/${UUID.randomUUID()}")
                val experienceRef = storageReference.child("experiences/${UUID.randomUUID()}")

                imageRef.putFile(imageUri!!).addOnCompleteListener { imageTask ->
                    if (imageTask.isSuccessful) {
                        imageRef.downloadUrl.addOnSuccessListener { imageUrl ->
                            experienceRef.putFile(experienceUri!!).addOnCompleteListener { experienceTask ->
                                if (experienceTask.isSuccessful) {
                                    experienceRef.downloadUrl.addOnSuccessListener { experienceUrl ->
                                        val tourGuide = TourGuide(
                                            name = name,
                                            location = location,
                                            price = price,
                                            description = description,
                                            languages = languages,
                                            tag1 = tag1,
                                            tag2 = tag2,
                                            imageUrl = imageUrl.toString(),
                                            experienceUrl = experienceUrl.toString(),
                                            customerCount = 0,
                                            tourCount = 0,
                                            totalEarnings = 0.0
                                        )
                                        database.child("tourGuides").child(userId).setValue(tourGuide).addOnCompleteListener {
                                            if (it.isSuccessful) {
                                                Toast.makeText(context, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                                                clearFields()
                                            } else {
                                                Log.e("FirebaseDatabase", "Error adding data", it.exception)
                                                Toast.makeText(context, "Gagal menambahkan data", Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    }
                                } else {
                                    Log.e("FirebaseStorage", "Error uploading experience", experienceTask.exception)
                                    Toast.makeText(context, "Gagal mengupload pengalaman", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    } else {
                        Log.e("FirebaseStorage", "Error uploading image", imageTask.exception)
                        Toast.makeText(context, "Gagal mengupload gambar", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            Toast.makeText(context, "Harap isi semua kolom dan pilih gambar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearFields() {
        etName.text.clear()
        etLocation.text.clear()
        etPrice.text.clear()
        etDescription.text.clear()
        etLanguages.text.clear()
        etTag1.text.clear()
        etTag2.text.clear()
        imageUri = null
        experienceUri = null
    }
}
