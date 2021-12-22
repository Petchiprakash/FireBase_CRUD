package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnUpdate.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val phoneNumber = binding.etPhoneNo.text.toString()
            updateData(firstName, lastName, phoneNumber)
        }
    }

    private fun updateData(firstName: String, lastName: String, phoneNumber: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        val user = mapOf(
            "firstName" to firstName,
            "lastName" to lastName,
            "phoneNo" to phoneNumber
        )
        database.child(phoneNumber).updateChildren(user).addOnSuccessListener {
            binding.etFirstName.text.clear()
            binding.etLastName.text.clear()
            binding.etPhoneNo.text.clear()
            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this, "Failed To Update", Toast.LENGTH_SHORT).show()
        }
    }
}