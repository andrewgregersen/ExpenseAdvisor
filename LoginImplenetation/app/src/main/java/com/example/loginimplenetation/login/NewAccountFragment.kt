package com.example.loginimplenetation.login

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.loginimplenetation.R
import com.example.loginimplenetation.databinding.FragmentNewAccountBinding
import com.google.firebase.auth.FirebaseAuth


class NewAccountFragment : Fragment() {


    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentNewAccountBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_new_account,
            container,
            false
        )
        binding.submitButton.setOnClickListener {
            val username = binding.createUser.text.toString()
            val pass1 = binding.createPass1.text.toString()
            val pass2 = binding.createPass2.text.toString()
            if (TextUtils.isEmpty(username)) {
                binding.createUser.error = "Email Required.."
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                binding.createUser.error = "This is not an email address..."
                binding.createUser.requestFocus()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(pass1)) {
                binding.createPass1.error = "Password Required..."
                binding.createPass1.requestFocus()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(pass2)) {
                binding.createPass2.error = "Please Confirm Password..."
                binding.createPass2.requestFocus()
                return@setOnClickListener
            }
            if (!TextUtils.equals(binding.createPass1.text, binding.createPass2.text)) {
                binding.createPass2.error = "Passwords do not match..."
                binding.createPass2.requestFocus()
                return@setOnClickListener
            }
            if (pass1.length <= 5) {
                binding.createPass1.error = "Password needs to be 6 characters or longer...."

            } else createAccount(username, pass1)
        }
        binding.goBack.setOnClickListener {
            this.findNavController()
                .navigate(NewAccountFragmentDirections.actionNewAccountFragmentToLoginFragment())
        }
        return binding.root
    }

    private fun createAccount(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this.requireActivity().parent) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG1, "createUserWithEmail:success")
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                    this.findNavController()
                        .navigate(NewAccountFragmentDirections.actionNewAccountFragmentToLoginFragment())
                } else {
                    Log.w(TAG1, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        binding.root.context,
                        "Authentication failed, please try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    companion object {
        private const val TAG1 = "EmailPassword"
    }
}


