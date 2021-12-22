package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.databinding.ActivityReadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityReadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRead.setOnClickListener {
            val phoneNo: String = binding.etPhoneNo.text.toString()
            if (phoneNo.isNotEmpty()) {
                readData(phoneNo)
            } else {
                Toast.makeText(this, "Please Enter The Phone Number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(phoneNo: String) {
        database= FirebaseDatabase.getInstance().getReference("Users")
        database.child(phoneNo).get().addOnSuccessListener {
            if (it.exists()){
                binding.etPhoneNo.text.clear()
                val firstName = it.child("firstName").value
                val lastName =it.child("lastName").value
                val phoneNumber =it.child("phoneNo").value
                Toast.makeText(this, "$firstName \n $lastName \n $phoneNumber", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "User Doesn't Exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed To Fetch Data", Toast.LENGTH_SHORT).show()
        }
    }
}
