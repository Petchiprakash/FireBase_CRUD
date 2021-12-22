package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnDelete.setOnClickListener {
            val phoneNo = binding.etPhoneNo.text.toString()
            if (phoneNo.isNotEmpty()){
                deleteData(phoneNo)
            }else{
                Toast.makeText(this, "Please Enter Valid Number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteData(phoneNo: String) {
        database=FirebaseDatabase.getInstance().getReference("Users")
        database.child(phoneNo).removeValue().addOnSuccessListener {
            Toast.makeText(this, "Successfully deleted", Toast.LENGTH_SHORT).show()
            binding.etPhoneNo.text.clear()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to delete", Toast.LENGTH_SHORT).show()
        }
    }
}