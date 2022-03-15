package com.example.intexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.intexp.databinding.ActivityPostexperienceBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Postexperience : AppCompatActivity() {
    private lateinit var binding: ActivityPostexperienceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostexperienceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.PEsaveBtn1.setOnClickListener {
            saveExperienceToFirebaseDatabase()
            val i= Intent(this, Experience::class.java)
            startActivity(i)
        }

    }
    private fun saveExperienceToFirebaseDatabase() {
        val uid = FirebaseAuth.getInstance().uid
        val ref1 = FirebaseDatabase.getInstance().getReference("/ExperienceDetails/")
        //val ref2 = FirebaseDatabase.getInstance().getReference("users")
//
//        if (uid != null) {
//            ref2.child(uid).get().addOnSuccessListener {
//                if (it.exists()) {
//                    farmername = it.child("username").value
//                }
//            }
//        }
        val experiencedetail = ExperienceDetails(
            uid!!,
            contributorname,
            binding.PECompanyNameEt.text.toString(),
            binding.PEOpportunityEt.text.toString(),
            binding.PEStipendEt.text.toString(),
            binding.PERoundsEt.text.toString(),
            binding.PEExperienceEt.text.toString(),
            binding.PETipsEt.text.toString(),
            binding.PEContactEt.text.toString()

        )
        //Log.d("TAGLOG", "saveCropToFirebaseDatabase: $name $location $weight $amount $note $date")
        //Log.d("okkkkkkkkkk", "saveCropToFirebaseDatabase: $experiencedetail")
        ref1.push().setValue(experiencedetail)
    }
}
data class ExperienceDetails(val Uid: String,val ContributorName: String?=null, val Company: String?=null, val Opportunity: String?=null
                             , val Stipend: String?=null, val Rounds: String?=null, val Experience: String?=null, val Tips: String?=null, val Contact: String?=null)