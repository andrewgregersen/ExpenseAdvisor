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


    override fun onStart() {
        super.onStart()
        mUser = FirebaseAuth.getInstance().currentUser
        setContentView(R.layout.logged_activity)
    }

    private fun getCurrentUser(mUser: FirebaseUser?){
        if(mUser != null){
            var name = mUser.displayName
            var photoUrl = mUser.photoUrl
        }
    }

    public override fun getIntent(): Intent? {
        return Intent.ACTION_GET_CONTENT
    }

    companion object{
        var intent = Intent.ACTION_CALL
    }




}