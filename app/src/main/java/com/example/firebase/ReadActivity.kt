package com.example.firebase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
            val lastN: String = binding.etLastName.text.toString()
            if (lastN.isNotEmpty()) {
                readData(lastN)
            } else {
                Toast.makeText(this, "Please Enter The Last Name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(phoneNo: String) {
        var count = 0
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.get().addOnSuccessListener {
            for (participantList in it.children) {
                if (phoneNo == participantList.child("lastName").value) {
//                    println(participantList.key)
                    binding.etLastName.text.clear()
                    val firstName = participantList.child("firstName").value
                    val lastName = participantList.child("lastName").value
                    val phoneNumber = participantList.child("phoneNo").value
                    Toast.makeText(
                        this,
                        "$firstName \n $lastName \n $phoneNumber",
                        Toast.LENGTH_LONG
                    ).show()
                    count ++
                } else if(count<0){
                    Toast.makeText(this, "User Doesn't Exist", Toast.LENGTH_SHORT).show()
                }
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed To Fetch Data", Toast.LENGTH_SHORT).show()
        }
    }
}
