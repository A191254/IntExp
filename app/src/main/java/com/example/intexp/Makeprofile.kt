package com.example.intexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.intexp.databinding.ActivityMakeprofileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Makeprofile : AppCompatActivity() {
    private lateinit var binding: ActivityMakeprofileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakeprofileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //FirebaseDatabase.getInstance().reference.child("Atharv").push().setValue("Wani")
        binding.registerme.setOnClickListener {
            saveUserToFirebaseDatabase()
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
    private fun saveUserToFirebaseDatabase() {
        //FirebaseDatabase.getInstance().reference.child("Atharv").push().setValue("Wani")
        val uid = FirebaseAuth.getInstance().uid
        Log.d("okkkkkkkkkkkkkk", "saveUserToFirebaseDatabase: $uid")
        val ref = FirebaseDatabase.getInstance().getReference("users").child("$uid")

//        val database = Firebase.database
//        val myRef = database.getReference("/users/")
//
//        myRef.setValue("Hello, World!")

        val user = User(uid!!,
            binding.NameEt.text.toString(),
            binding.PhoneNumberEt.text.toString(),
            binding.LocationEt.text.toString()
        )
        Log.d("okkkkkkkkkkkkkk", "saveUserToFirebaseDatabase: ${user.username} ${user.location}")

        ref.setValue(user)


    }
}
class User(val uid: String ,val username: String, val mobileno: String, val location: String)