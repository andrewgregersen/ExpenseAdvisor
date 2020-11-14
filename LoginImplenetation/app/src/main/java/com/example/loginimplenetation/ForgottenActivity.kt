package com.example.loginimplenetation

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.loginimplenetation.databinding.ForgottenActivityBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.new_account_activity.*

class ForgottenActivity: AppCompatActivity() {

    private lateinit var binding : ForgottenActivityBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ForgottenActivityBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        setContentView(R.layout.forgotten_activity)

        display()
    }




    private fun display(){
        val btnreset = binding.button2
        btnreset.setOnClickListener {
            if(TextUtils.isEmpty(binding.email.text.toString().trim())){
                binding.email.error = "Email Required.."
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(binding.email.text.toString().trim()).matches()){
                binding.email.error = "This is not an email address..."
                binding.email.requestFocus()
                return@setOnClickListener
            }
            auth.sendPasswordResetEmail(binding.email.text.toString().trim())
        }
    }
}