package com.example.intexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.intexp.databinding.ActivityMakeprofileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_makeprofile.*

class Makeprofile : AppCompatActivity() {
    private lateinit var binding: ActivityMakeprofileBinding
    lateinit var category: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakeprofileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //FirebaseDatabase.getInstance().reference.child("Atharv").push().setValue("Wani")
        binding.registerme.setOnClickListener {
            val username =binding.NameEt.text.toString()
            val location = binding.PhoneNumberEt.text.toString()
            val mobileno = binding.LocationEt.text.toString()

            if(binding.rbsenior.isChecked)
                category="Senior"
            else if(binding.rbjunior.isChecked)
                category="Junior"
            else if(binding.rbspc.isChecked)
                category="SPC"

            if(username.isEmpty() || location.isEmpty() || mobileno.isEmpty() ){
                Toast.makeText(this,"Please enter all details ! ",Toast.LENGTH_SHORT).show()
            }
            else {
                saveUserToFirebaseDatabase()
                startActivity(Intent(this, MainActivity::class.java))
            }
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
            binding.LocationEt.text.toString(),
            category
        )
        Log.d("okkkkkkkkkkkkkk", "saveUserToFirebaseDatabase: ${user.username} ${user.location}")

        ref.setValue(user)


    }
}
class User(val uid: String ,val username: String, val mobileno: String, val location: String, val category: String)