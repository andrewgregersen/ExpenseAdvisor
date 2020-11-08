package com.example.loginimplenetation

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.loginimplenetation.databinding.NewAccountActivityBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.new_account_activity.*

class NewAccount: AppCompatActivity(){

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: NewAccountActivityBinding
    private lateinit var view : View

    override  fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = NewAccountActivityBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(R.layout.new_account_activity)

        activity()

    }

    private fun activity(){
        val submit = findViewById<Button>(binding.submitButton.id)
        submit.setOnClickListener {
            val username = binding.createUser.text.toString().trim()
            val pass1 = binding.createPass1.text.toString().trim()
            val pass2 = binding.createPass2.text.toString().trim()
            if(TextUtils.isEmpty(username)){
                createUser.error = "Email Required.."
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(pass1)){
                createPass1.error = "Password Required..."
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(pass2)){
                createPass2.error = "Please Confirm Password..."
            }
            if(!TextUtils.equals(createPass1.text,createPass2.text)){
                Snackbar.make(view, "Passwords do not match", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }else createAccount(username,pass1)
        }
        val back = findViewById<TextView>(binding.goBack.id)
        back.setOnClickListener{
            finish()
        }
    }

    private fun createAccount(email: String, password: String){

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    Log.d(MainActivity.TAG1, "createUserWithEmail:success")
                    val user = auth.currentUser
                    finish()
                }else{
                    Log.w(MainActivity.TAG1, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    companion object{
        private const val TAG1 = "EmailPassword"
    }
}

