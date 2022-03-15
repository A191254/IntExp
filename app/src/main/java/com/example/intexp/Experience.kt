package com.example.intexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.intexp.databinding.ActivityExperienceBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.R

class Experience : AppCompatActivity() {
    private lateinit var dbRef : DatabaseReference
    val uid = FirebaseAuth.getInstance().uid
    private lateinit var myrecyclerview : RecyclerView
    private lateinit var mylist1 : ArrayList<ExperienceDetails>
    private lateinit var expdetail : ExperienceDetails
    private lateinit var binding : ActivityExperienceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExperienceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myrecyclerview = binding.listofexperiences
        myrecyclerview.layoutManager = LinearLayoutManager(this)
        mylist1 = arrayListOf<ExperienceDetails>()
        getExpdata()



    }
    private fun getExpdata() {

        dbRef = FirebaseDatabase.getInstance().getReference("ExperienceDetails")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        Log.d("TAG", "onDataChange1: $userSnapshot")
                        expdetail = userSnapshot.getValue(ExperienceDetails::class.java)!!
                        mylist1.add(expdetail)
                    }
                    //Log.d("okkk", "onDataChange: $mylist1")
                    myrecyclerview.adapter = MyAdapterExperience(mylist1)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
    fun openNewTask1(view: View) {
        startActivity(Intent(this, Postexperience::class.java))
    }
}