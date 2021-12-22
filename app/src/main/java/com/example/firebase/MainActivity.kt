package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSubmit.setOnClickListener {

            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val phoneNumber = binding.etPhoneNo.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")
            val user = UserData(firstName, lastName, phoneNumber)
            database.child(phoneNumber).setValue(user).addOnSuccessListener {

                binding.etFirstName.text.clear()
                binding.etLastName.text.clear()
                binding.etPhoneNo.text.clear()

                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
            }.addOnCanceledListener {
                Toast.makeText(this, "Failed To Save", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnRead.setOnClickListener {
            val intent = Intent(this, ReadActivity::class.java)
            startActivity(intent)
        }
        binding.btnUpdate.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            startActivity(intent)
        }
        binding.btnDelete.setOnClickListener {
            val intent = Intent(this, DeleteActivity::class.java)
            startActivity(intent)
        }
    }
}