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
import kotlinx.android.synthetic.main.logged_activity.*
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

    //displays the vairous buttons and graphs on the screen
    private fun displayStuff(){
        val btnsignout = findViewById<Button>(binding.signOut.id)
        btnsignout.setOnClickListener {
            logOut()
        }
        val txtnamehere = findViewById<TextView>(binding.putName.id)
        txtnamehere.text = auth.currentUser?.email
        val btnPhoto = findViewById<Button>(binding.Photos.id)
        btnPhoto.setOnClickListener {
            val intent = Intent(this@LoggedInActivity, CameraAccessActivity::class.java)
            startActivity(intent)
        }
        val btnSettings =findViewById<Button>(binding.Settings.id)
        btnSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        //access the database where the individual receipts are stored
        val bntDatabase = findViewById<Button>(binding.database.id)
        bntDatabase.setOnClickListener {
           // val intent = Intent(this,DatabaseActivity::class.java)
            //startActivity(intent)
            val message = findViewById<TextView>(binding.message.id)
            message.text = R.string.DBAccess.toString()
        }

    }


    //logs the user out of the application
    private fun logOut(){
        auth.signOut()

        finish()
    }




}

