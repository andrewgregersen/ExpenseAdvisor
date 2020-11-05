package com.example.loginimplenetation

import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase

class LoggedInActivity: AppCompatActivity(){

    private var mUser : FirebaseUser? = null
    private lateinit var auth : FirebaseAuth


    override fun onStart() {
        super.onStart()
        auth = FirebaseAuth.getInstance()
        mUser = FirebaseAuth.getInstance().currentUser
        setContentView(R.layout.logged_activity)
    }

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
    }

    private fun getCurrentUser(mUser: FirebaseUser?){
        if(mUser != null){
            var name = mUser.displayName
            var photoUrl = mUser.photoUrl
        }
    }

    private fun signOut(){
        auth.signOut()
        finish()
    }



}

