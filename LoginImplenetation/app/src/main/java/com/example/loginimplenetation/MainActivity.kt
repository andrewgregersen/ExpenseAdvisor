package com.example.loginimplenetation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.loginimplenetation.databinding.ActivityMainBinding
import com.example.loginimplenetation.databinding.NewAccountFragmentBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


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
        var gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = Firebase.auth

        actions()
    }

    private fun actions() {
        val googleButton = findViewById<Button>(R.id.google_Login)
        val facebookButton = findViewById<Button>(R.id.facebook_Login)
        val loginButton = findViewById<Button>(R.id.Login)
        val newAccount = findViewById<Button>(R.id.newAccount)
        val reset = findViewById<Button>(R.id.forgot)

        googleButton.setOnClickListener(View.OnClickListener {
            fun onClick(view: View) {
                signIn()
            }
        })


        loginButton.setOnClickListener(View.OnClickListener {
            fun onClick(view: View) {
                val email = Username.text.toString().trim()
                val pass = Password.text.toString().trim()

                if (email.isEmpty()) {
                    Username.error = "Email Required..."
                    return
                }
                if (pass.isEmpty()) {
                    Password.error = "Password Required"
                    return
                }
                signIn(email, pass)
            }
        })


    }







    //sign in for google
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











override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG2, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            }catch (e: Exception){
                Log.w(TAG2, "Google sign in failed", e)
                updateUI(null)
            }
        }
    }

    private fun firebaseAuthWithGoogle(token: String){
        val credential = GoogleAuthProvider.getCredential(token, null)
        auth.signInWithCredential(credential).addOnCompleteListener(this){ task ->
            if(task.isSuccessful){
                Log.d(TAG2, "signInWithCredential:success")
                val user = auth.currentUser
                updateUI(user)
            }else{
                Log.w(TAG2, "signInWithCredential:failure", task.exception)
                val view = binding.coordLayout
                Snackbar.make(view, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }



    private fun createAccount(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    Log.d(TAG1, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                }else{
                    Log.w(TAG1, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }




    //updates the UI for the activity either logging a person in or out
    private fun updateUI(user: FirebaseUser?){
        if(user != null){
            val intent = Intent(this@MainActivity, LoggedInActivity::class.java)
            startActivity(intent)
            updateUI(auth.currentUser)
        }else{
            Snackbar.make(binding.coordLayout,"You have signed out",Snackbar.LENGTH_SHORT).show()
            binding.googleLogin.visibility = View.VISIBLE
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


