package com.example.loginimplenetation

import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.loginimplenetation.databinding.LoggedActivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase

class LoggedInActivity: AppCompatActivity(){

    private var mUser : FirebaseUser? = null
    private lateinit var auth : FirebaseAuth
    private lateinit var binding : LoggedActivityBinding


    override fun onStart() {
        super.onStart()
        auth = FirebaseAuth.getInstance()
        mUser = FirebaseAuth.getInstance().currentUser
        setContentView(R.layout.logged_activity)
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

    fun getView(): ConstraintLayout {
        return binding.root
    }



}

