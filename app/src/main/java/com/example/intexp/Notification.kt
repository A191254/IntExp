package com.example.intexp

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.intexp.databinding.ActivityNotificationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlin.math.log

class Notification : AppCompatActivity() {
    private lateinit var dbRef : DatabaseReference
    var uid = FirebaseAuth.getInstance().uid
    private lateinit var myrecyclerview : RecyclerView
    private lateinit var mylist2 : ArrayList<NotificationDetails>
    lateinit var notifdetail : NotificationDetails
    private lateinit var binding: ActivityNotificationBinding
    var c: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myrecyclerview = binding.listofnotifications
        myrecyclerview.layoutManager = LinearLayoutManager(this)
        mylist2 = arrayListOf<NotificationDetails>()
        getNotifdata()
    }
    private fun getNotifdata() {

        dbRef = FirebaseDatabase.getInstance().getReference("NotificationDetails")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children){
                        //val nd = userSnapshot.getValue(NotificationDetails::class.java)!!
                        //val nd = userSnapshot.getValue(NotificationDetails::class.java)
                        var a = userSnapshot.child("companyName").value.toString()
                        var b = userSnapshot.child("date").value.toString()
                        var c = userSnapshot.child("eligiblity").value.toString()
                        var d = userSnapshot.child("jobProfile").value.toString()
                        var e = userSnapshot.child("opportunity").value.toString()
                        var f = userSnapshot.child("stipend").value.toString()
                        var g = userSnapshot.child("uid").value.toString()

                        Log.d("TAG", "x : $a $b $c $d $e $f ")
                        val nd = NotificationDetails(g,a,e,f,c,d,b)
                        mylist2.add(nd!!)
                    }
                    myrecyclerview.adapter = MyAdapterNotification(mylist2)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
    fun openNewTask2(view: View) {

        var Ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("users")
        var category: String = ""

        Ref.child("$uid").get().addOnSuccessListener {

            if(it.exists()){
                val cat = it.child("category")
                Log.d("TAG", "openNewTask2: $cat ")
                if(cat.value.toString() == "SPC")
                    startActivity(Intent(this, Postnotification::class.java))
                else{

                }
            }

        }

        Log.d("TAG", "$c ")

    }
}
