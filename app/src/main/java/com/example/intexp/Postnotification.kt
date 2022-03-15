package com.example.intexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intexp.databinding.ActivityNotificationBinding
import com.example.intexp.databinding.ActivityPostexperienceBinding
import com.example.intexp.databinding.ActivityPostnotificationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Postnotification : AppCompatActivity() {
    private lateinit var binding: ActivityPostnotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostnotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.PNsaveBtn1.setOnClickListener {
            saveNotificationToFirebaseDatabase()
            val i= Intent(this, Notification::class.java)
            startActivity(i)
        }
    }
    private fun saveNotificationToFirebaseDatabase() {
        val uid = FirebaseAuth.getInstance().uid
        val ref1 = FirebaseDatabase.getInstance().getReference("/NotificationDetails/")

        val notificationdetail = NotificationDetails(
            binding.PNCompanyNameEt.text.toString(),
            binding.PNOpportunityEt.text.toString(),
            binding.PNStipendEt.text.toString().toInt(),
            binding.PNEligiblityEt.text.toString(),
            binding.PNJobProfileEt.text.toString(),
        )
        //Log.d("TAGLOG", "saveCropToFirebaseDatabase: $name $location $weight $amount $note $date")

        ref1.child("$uid").setValue(notificationdetail)
    }
}
data class NotificationDetails(val CompanyName: String?=null, val Opportunity: String?=null, val Stipend: Int?=null,
                               val Eligiblity: String?=null, val JobProfile: String?=null, val Date: String?=null)