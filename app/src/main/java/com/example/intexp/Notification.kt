package com.example.intexp

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.intexp.databinding.ActivityNotificationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Notification : AppCompatActivity() {
    private lateinit var dbRef : DatabaseReference
    val uid = FirebaseAuth.getInstance().uid
    private lateinit var myrecyclerview : RecyclerView
    private lateinit var mylist2 : ArrayList<NotificationDetails>
    private lateinit var notifdetail : NotificationDetails
    private lateinit var binding: ActivityNotificationBinding
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

//        dbRef.child("$uid").get().addOnSuccessListener {
//            if(it.exists()){
//                val cn=it.child("company").value.toString()
//                val oppr=it.child("opportunity").value.toString()
//                val eli=it.child("eligiblity").value.toString()
//                val jp=it.child("jobProfile").value.toString()
//                val sti=it.child("stipend").value
//                val dt=it.child("date").value.toString()
//                val notifdetail = NotificationDetails(cn,oppr, sti as Int?,eli,jp,dt)
//                Log.d("TAG", "getNotifdata: $notifdetail")
//                mylist2.add(notifdetail)
//                myrecyclerview.adapter = MyAdapterNotification(mylist2)
//            }
//            //myrecyclerview.adapter = MyAdapterNotification(mylist2)
//            //val notifdetail = NotificationDetails(cn,oppr,sti,eli,jp,dt)
//        }
        //myrecyclerview.adapter = MyAdapterNotification(mylist2)


        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        notifdetail = userSnapshot.getValue(NotificationDetails::class.java)!!
                        mylist2.add(notifdetail)
                    }
                    myrecyclerview.adapter = MyAdapterNotification(mylist2)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
    fun openNewTask2(view: View) {
        startActivity(Intent(this, Postnotification::class.java))
    }
}
