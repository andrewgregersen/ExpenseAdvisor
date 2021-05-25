package com.example.loginimplenetation

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginimplenetation.databinding.ForgottenActivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*

class ForgottenActivity: AppCompatActivity() {

    private lateinit var binding : ForgottenActivityBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ForgottenActivityBinding.inflate(layoutInflater)
        auth = Firebase.auth
        setContentView(R.layout.forgotten_activity)
        val resetBtn = findViewById<Button>(binding.reset.id)
        resetBtn.setOnClickListener {
            val email = findViewById<EditText>(R.id.Email)
            if (TextUtils.isEmpty(email.text.toString().trim())) {
                email.error = "Please enter an email address..."
                email.requestFocus()
                //println("Is Empty")
                return@setOnClickListener

            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString().trim()).matches()) {
                email.error = "This is not an email address..."
                email.requestFocus()
                //println("Not an Email")
                return@setOnClickListener
            }
            auth.sendPasswordResetEmail(email.text.toString().trim())
            Toast.makeText(this, "Please Check Your Email to Reset Your Password!",Toast.LENGTH_SHORT).show()
            finish()



        }
    }





}