package com.example.loginimplenetation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.loginimplenetation.LoggedInActivity
import com.example.loginimplenetation.R
import com.example.loginimplenetation.databinding.FragmentLoginBinding
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


class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var binding: FragmentLoginBinding
    private var noAccount = true
    private var debug = false

    override fun onStart() {
        super.onStart()
        //checks to see if there is an already signed in account
        val currentUser = auth.currentUser
        noAccount = false
        updateUI(currentUser)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(secretkey).requestEmail().build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        auth = Firebase.auth

        binding.lifecycleOwner = viewLifecycleOwner


        binding.newAccount.setOnClickListener {
            this.findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToNewAccountFragment())
        }

        binding.forgot.setOnClickListener {
            this.findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToForgottenAccountFragment())
        }

        binding.Login.setOnClickListener {
            if (binding.Username.text.isNullOrEmpty()) {
                binding.Username.error = "Please enter a username!"
                binding.Username.requestFocus()
            }
            if (binding.Password.text.isNullOrEmpty()) {
                binding.Password.error = "Please enter a password!"
                binding.Password.requestFocus()
            } else {
                signIn(binding.Username.text.toString(), binding.Password.text.toString())
            }
        }

        binding.googleLogin.setOnClickListener {
            signIn()
        }
        return binding.root
    }

    //sign in for google, starts a google activity
    private fun signIn() {
        val intent = mGoogleSignInClient.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)
    }

    //sign in for email
    private fun signIn(email: String, password: String) {
        activity?.let {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG1, "signInWIthEmail:success")
                        val user = auth.currentUser
                        if (user != null) {
                            updateUI(user)
                        }
                    } else {
                        Log.w(TAG1, "signInWithEmail:failure", task.exception)
                        Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }

    }


    //gives the result for the google login, would update the interface
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG2, "firebaseAuthWithGoogle: " + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w(TAG2, "Google sign in failed", e)
            }
        }/*else{
            callbackManager.onActivityResult(requestCode,resultCode,data)
        }*/


    }


    private fun firebaseAuthWithGoogle(token: String) {
        val credential = GoogleAuthProvider.getCredential(token, null)
        activity?.let {
            auth.signInWithCredential(credential)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG2, "signInWithCredential:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        Log.w(TAG2, "SignInWithCredential:failure", task.exception)
                        Snackbar.make(binding.root, "Authentication Failed", Snackbar.LENGTH_SHORT)
                            .show()
                        updateUI(null)
                    }
                }
        }
    }


    //updates the UI for the activity either logging a person in or out
    private fun updateUI(user: FirebaseUser?) {

        if (debug) {
            val intent = Intent(context, LoggedInActivity::class.java)
            startActivity(intent)

        } else if (user != null) {
            this.findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToLoggedInActivity())
            binding.Username.text.clear()
            binding.Password.text.clear()
        } else {
            Toast.makeText(context, "Login has failed, please sign-in", Toast.LENGTH_SHORT).show()
            binding.googleLogin.visibility = View.VISIBLE
        }


    }


    companion object {
        const val TAG1 = "EmailPassword"
        private const val TAG2 = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
        private const val secretkey =
            "518692453774-bdhq1tsv3172bi7svmodd72l9fumnjro.apps.googleusercontent.com"
    }
}