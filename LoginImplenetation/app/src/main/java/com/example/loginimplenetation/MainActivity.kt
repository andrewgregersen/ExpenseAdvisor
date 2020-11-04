package com.example.loginimplenetation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.loginimplenetation.databinding.ActivityMainBinding
import com.example.loginimplenetation.databinding.NewAccountFragmentBinding
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


class MainActivity: AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var binding: ActivityMainBinding

    public override fun onStart() {
        super.onStart()
        //checks to see if there is an already signed in account
        var account = GoogleSignIn.getLastSignedInAccount(this)
        val currentUser = auth.currentUser
        if (currentUser == null) {
            if (account == null) {
                //if (faccount == null) {
                  //  updateUI(null)
                //}
            } else signIn()
        } else updateUI(currentUser)


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        val Snackbar = Snackbar.make(view,coordLayout.id,5)
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = Firebase.auth
        val googleButton = findViewById<Button>(R.id.google_Login).setOnClickListener(google_Login)
        //val facebookButton = findViewById<Button>(R.id.facebook_Login).setOnClickListener(facebook_Login)
        val loginButton = findViewById<Button>(R.id.Login).setOnClickListener(){
            signIn(R.id.Username.toString(),R.id.Password.toString())
        }
        val newAccount = binding.newAccount.setOnClickListener(){
            setContentView(NewAccountFragmentBinding)
        }






    }






    override fun onClick(v:View) {
        when (v.id) {
            R.id.Login -> signIn(R.id.Username.toString(),R.id.Password.toString())
            R.id.google_Login -> signIn()
            //R.id.facebook_Login -> signIn() need to implement
            R.id.signout -> signOut() //need to implement


        }
    }

    //sign in for google
    private fun signIn(){
        val intent = mGoogleSignInClient.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)
    }











override fun onActivityResult(requestCode: Int,resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode,resultCode,data)

        if(requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG2, "firebaseAuthWithGoogle:"+ account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            }catch (e: Exception){
                Log.w(TAG2, "Google sign in failed" ,e)
                updateUI(null)
            }
        }
    }

    private fun firebaseAuthWithGoogle(token: String ){
        val credential = GoogleAuthProvider.getCredential(token, null)
        auth.signInWithCredential(credential).addOnCompleteListener(this){ task ->
            if(task.isSuccessful){
                Log.d(TAG2, "signInWithCredential:success")
                val user = auth.currentUser
                updateUI(user)
            }else{
                Log.w(TAG2, "signInWithCredential:failure",task.exception)
                val view = binding.mainLayout
                Snackbar.make(view,"Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }

    private fun createSignInIntent(){
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
    }

    private fun signOut(){
        auth.signOut()

        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this){
            updateUI(null)
        }
    }

    private fun createAccount(email: String, password: String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    Log.d(TAG1, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                }else{
                    Log.w(TAG1, "createUserWithEmail:failure",task.exception)
                    Toast.makeText(baseContext, "Authentication failed", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

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
                    Toast.makeText(baseContext,"Authentication failed", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }

    }

    //updates the UI for the activity either logging a person in or out
    private fun updateUI(user: FirebaseUser?){
        if(user != null){
            binding.status.text = getString(R.string.google_status_fmt, user.email)
            binding.detail.text = getString(R.string.firebase_status_fmt,user.uid)


            binding.googleLogin.visibility = View.VISIBLE
            //binding.signOutAndDisconect.visibility = View.GONE not implemented yet
        }else{
            binding.status.setText(R.string.signed_out)
            binding.detail.text = null
            binding.google_Login.visibility = View.VISIBLE
            binding.signOutAndDisconnect.visibility = View.GONE
        }

        class NewAccountFragment : Fragment(){

            private lateinit var binding1: NewAccountFragmentBinding
            override fun onCreateView(
                inflater: LayoutInflater,
                container: ViewGroup?,
                savedInstanceState: Bundle?
            ): View? {
                return inflater.inflate(R.layout.New_Account_Fragment, container, false)
            }

            override  fun onCreate(savedInstanceState: Bundle?){
                super.onCreate(savedInstanceState)
                binding1 = NewAccountFragmentBinding.inflate(layoutInflater)
                val v = binding1.root




            }
        }

    }


    companion object {
        private const val TAG1 = "EmailPassword"
        private const val TAG2 = "GoogleActivity"
        private const val RC_MULTI_FACTOR = 9005
        private const val RC_SIGN_IN = 9001
    }




}


