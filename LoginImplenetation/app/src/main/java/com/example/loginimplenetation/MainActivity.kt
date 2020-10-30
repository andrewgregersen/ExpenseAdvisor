package com.example.loginimplenetation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Switch
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import java.lang.Exception


class MainActivity: AppCompatActivity(){
    private lateinit var auth : FirebaseAuth

    public override fun onStart(){
        super.onStart()
        //checks to see if there is an already signed in account
        var account = GoogleSignIn.getLastSignedInAccount(this)
        val currentUser = auth.currentUser
        if(currentUser == null){
            if(account==null){
                if(faccount == null){
                    updateUI(null)
                }
            }else updateUI(account)
        }else updateUI(currentUser)



    }


    private fun googleSignIn(){
         val signInIntent = mGoogleSignInClient.signInIntent
        startActrivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int,resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode,resultCode,data)

        if(requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:"+ account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            }catch (e: Exception){
                Log.w(TAG, "Google sign in failed" ,e)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        var mGoogleSignInClient = GoogleSignIn.getClient(this,gso)
        findViewById(R.id.google_Login).setOnClickListener(this)

    }

    private fun createSignInIntent(){
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
    }

    private fun createAccount(email: String, password: String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                }else{
                    Log.w(TAG, "createUserWithEmail:failure",task.exception)
                    Toast.makeText(baseContext, "Authentication failed", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun signIn(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task->
                if(task.isSuccessful){
                    Log.d(TAG, "signInWIthEmail:success")
                    val user = auth.currentUser
                    if (user != null) {
                        updateUI(user)
                    }
                }else{
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext,"Authentication failed", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }

    }

    private fun updateUI(user: FirebaseUser?){
        if(user != null){
            LoggedInActivity()
        }

    }
    private fun updateUI(user: GoogleSignInAccount){
        if(user != null){

        }

    }


    companion object {
        private const val TAG = "EmailPassword"
        private const val RC_MULTI_FACTOR = 9005
    }


}

