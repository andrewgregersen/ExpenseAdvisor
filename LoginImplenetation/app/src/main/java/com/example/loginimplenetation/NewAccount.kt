package com.example.loginimplenetation

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginimplentation.R
import com.example.loginimplentation.databinding.NewAccountActivityBinding
import com.google.firebase.auth.FirebaseAuth

class NewAccount: AppCompatActivity(){

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: NewAccountActivityBinding
    private lateinit var view : View

    override  fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = NewAccountActivityBinding.inflate(layoutInflater)
        view = binding.root
        auth = FirebaseAuth.getInstance()
        setContentView(R.layout.new_account_activity)

        activity()

    }

    private fun activity(){
        val submit = findViewById<Button>(binding.submitButton.id)
        submit.setOnClickListener {
            val username = findViewById<EditText>(binding.createUser.id).text.toString().trim()
            val pass1 = findViewById<EditText>(binding.createPass1.id).text.toString().trim()
            val pass2 = findViewById<EditText>(binding.createPass2.id).text.toString().trim()
            if(TextUtils.isEmpty(username)){
                binding.createUser.error = "Email Required.."
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()){
                binding.createUser.error = "This is not an email address..."
                binding.createUser.requestFocus()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(pass1)){
                binding.createPass1.error = "Password Required..."
                binding.createPass1.requestFocus()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(pass2)){
                binding.createPass2.error = "Please Confirm Password..."
                binding.createPass2.requestFocus()
                return@setOnClickListener
            }
            if(!TextUtils.equals(binding.createPass1.text,binding.createPass2.text)){
                binding.createPass2.error = "Passwords do not match..."
                binding.createPass2.requestFocus()
                return@setOnClickListener
            }
            if(pass1.length <=5){
                binding.createPass1.error = "Password needs to be 6 characters or longer...."

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
                    user?.sendEmailVerification()
                    finish()
                }else{
                    Log.w(MainActivity.TAG1, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed, please try again", Toast.LENGTH_SHORT).show()
                }
            }
    }

    companion object{
        private const val TAG1 = "EmailPassword"
    }
}

