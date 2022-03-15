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
        //dbRef = FirebaseDatabase.getInstance().getReference("NotificationDetails")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children){
                        //val nd = userSnapshot.getValue(NotificationDetails::class.java)!!
                        //val nd = userSnapshot.getValue(NotificationDetails::class.java)
                        var a = userSnapshot.child("company").value.toString()
                        var b = userSnapshot.child("contact").value.toString()
                        var c = userSnapshot.child("contributorName").value.toString()
                        var d = userSnapshot.child("experience").value.toString()
                        var e = userSnapshot.child("opportunity").value.toString()
                        var f = userSnapshot.child("rounds").value.toString()
                        var g = userSnapshot.child("stipend").value.toString()
                        var h = userSnapshot.child("tips").value.toString()
                        var i = userSnapshot.child("uid").value.toString()
                        //Log.d("TAG", "x : $a $b $c $d $e $f $g $h")
                        Log.d("TAG", "x : $d")
                        val ed = ExperienceDetails(i,c,a,e,g,f,d,h,b)
                        mylist1.add(ed!!)
                    }
                    myrecyclerview.adapter = MyAdapterExperience(mylist1)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
    fun openNewTask1(view: View) {
        var Ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("users")
        var category: String = ""

        Ref.child("$uid").get().addOnSuccessListener {

            if(it.exists()){
                val cat = it.child("category")
                Log.d("TAG", "openNewTask2: $cat ")
                if(cat.value.toString() == "SPC" || cat.value.toString() == "Senior")
                    startActivity(Intent(this, Postexperience::class.java))
                else{

                }
            }

        }

    }
}