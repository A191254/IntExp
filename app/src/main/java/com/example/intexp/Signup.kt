package com.example.intexp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.intexp.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_signup.*

class Signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
//    private var email = ""
//    private var password = ""
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.signUpBtn.setOnClickListener {
            Log.d(TAG, "onCreate: signupbtnclicked")
            Toast.makeText(this,"Enter a Valid Email-Address",Toast.LENGTH_LONG).show()
            val email = binding.usernameEt.text.toString().trim()
            val password = binding.passwordEt.text.toString().trim()
            //auth.createUserWithEmailAndPassword(email, password)
            //Toast.makeText(this,"Clicked",Toast.LENGTH_LONG).show()
            createAccount(email, password)

            //startActivity(Intent(this, Makeprofile::class.java))
        }

    }
    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        Log.d(TAG, "createAccount: $email $password")
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Log.d(TAG, "createAccount: failed")
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        // [END create_user_with_email]

    }

    private fun updateUI(user: FirebaseUser?) {
        startActivity(Intent(this, Makeprofile::class.java))
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}