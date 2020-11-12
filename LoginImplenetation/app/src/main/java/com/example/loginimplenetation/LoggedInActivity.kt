package com.example.loginimplenetation

import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.loginimplenetation.databinding.LoggedActivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import kotlin.math.sign

class LoggedInActivity: AppCompatActivity(){

    private var mUser : FirebaseUser? = null
    private lateinit var auth : FirebaseAuth
    private lateinit var binding : LoggedActivityBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        mUser = FirebaseAuth.getInstance().currentUser
        binding = LoggedActivityBinding.inflate(layoutInflater)
        setContentView(R.layout.logged_activity)

        displayStuff()
    }

    private fun displayStuff(){
        val btnsignout = findViewById<Button>(binding.signOut.id)
        btnsignout.setOnClickListener {
            logOut()
        }
        val txtnamehere = findViewById<TextView>(binding.putName.id)
        txtnamehere.text = auth.currentUser?.displayName
    }

    private fun logOut(){
        auth.signOut()
        finish()
    }

    fun getView(): ConstraintLayout {
        return binding.root
    }



}

