package com.example.loginimplenetation

import android.R
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginimplenetation.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity: AppCompatActivity() {


    val Extra_Message_1 = "com.example.loginimplenetation.NewAccount"
    private lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var binding: ActivityMainBinding

    public override fun onStart() {
        super.onStart()
        //checks to see if there is an already signed in account
        val currentUser = auth.currentUser
        updateUI(currentUser)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        auth = Firebase.auth
        var view = binding.root
        setContentView(view)
        var gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("518692453774-bdhq1tsv3172bi7svmodd72l9fumnjro.apps.googleusercontent.com").requestEmail().build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = Firebase.auth


        //login button that is in conjoined to the create new account (Email)
        val login = findViewById<Button>(binding.Login.id)
        login.setOnClickListener {
            val username = binding.Username.text.toString().trim()
            val password = binding.Password.text.toString().trim()
            if(TextUtils.isEmpty(username)){
                binding.Username.error = "Email Required..."
                binding.Username.requestFocus()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(password)){
                binding.Password.error = "Password Required..."
                binding.Password.requestFocus()
                return@setOnClickListener
            }
            signIn(username, password)
        }

        //implements the google login button
        val googleButton = findViewById<com.google.android.gms.common.SignInButton>(binding.googleLogin.id)
        googleButton.setOnClickListener {
            signIn()
        }

        //implements the new account button (sperate email that is not necessarily a google one)
        val newAccount = findViewById<Button>(binding.newAccount.id)
        newAccount.setOnClickListener{
            val intent = Intent(this, NewAccount::class.java).apply{
                putExtra(Extra_Message_1,"do it")
            }
            startActivity(intent)
        }

        //debug button to get to the Logged in activity, skipping the login sequence
        val nextScreen = findViewById<Button>(binding.skip.id)
        nextScreen.setOnClickListener {
            val intent = Intent(this, LoggedInActivity::class.java)
            startActivity(intent)
        }


    }









    //sign in for google, starts a google activity
    private fun signIn(){
        val intent = mGoogleSignInClient.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)
    }

    //sign in for email
    private fun signIn(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task->
                if(task.isSuccessful){
                    Log.d(TAG1, "signInWIthEmail:success")
                    val user = auth.currentUser
                    if (user != null) {
                        updateUI(user)
                    }
                }else{
                    Log.w(TAG1, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }

    }










//gives the result for the google login, would update the interface
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG2, "firebaseAuthWithGoogle: "+ account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            }catch(e:ApiException){
                Log.w(TAG2,"Google sign in failed",e)
            }
        }


    }


    private fun firebaseAuthWithGoogle(token: String){
        val credential = GoogleAuthProvider.getCredential(token,null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this){
                task -> if(task.isSuccessful){
                Log.d(TAG2,"signInWithCredential:success")
                val user = auth.currentUser
                updateUI(user)
            }else{
                Log.w(TAG2, "SignInWithCredential:failure",task.exception)
                Snackbar.make(binding.root, "Authentication Failed",Snackbar.LENGTH_SHORT).show()
                updateUI(null)
            }
            }
    }












    //updates the UI for the activity either logging a person in or out
    private fun updateUI(user: FirebaseUser?){
        if(user != null){
            val intent = Intent(this@MainActivity, LoggedInActivity::class.java)
            startActivity(intent)
            binding.Username.text.clear()
            binding.Password.text.clear()
        }else{
            Snackbar.make(binding.coordLayout, "You have signed out", Snackbar.LENGTH_SHORT).show()
            binding.googleLogin.visibility = View.VISIBLE
        }



    }



    companion object {
        const val TAG1 = "EmailPassword"
        private const val TAG2 = "GoogleActivity"
        private const val RC_MULTI_FACTOR = 9005
        private const val RC_SIGN_IN = 9001
        private const val web_server_key = "518692453774-bdhq1tsv3172bi7svmodd72l9fumnjro.apps.googleusercontent.com"
    }




}


