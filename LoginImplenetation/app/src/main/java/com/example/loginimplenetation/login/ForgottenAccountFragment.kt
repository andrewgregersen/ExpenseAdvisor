package com.example.loginimplenetation.login

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.loginimplenetation.R
import com.example.loginimplenetation.databinding.FragmentForgottenAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ForgottenAccountFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentForgottenAccountBinding>(
                inflater,
                R.layout.fragment_forgotten_account, container, false
            )
        auth = Firebase.auth
        binding.reset.setOnClickListener {

            if (TextUtils.isEmpty(binding.Email.text.toString().trim())) {
                binding.Email.error = "Please enter an email address..."
                binding.Email.requestFocus()
                //println("Is Empty")
                return@setOnClickListener

            }
            if (!Patterns.EMAIL_ADDRESS.matcher(binding.Email.text.toString().trim()).matches()) {
                binding.Email.error = "This is not an email address..."
                binding.Email.requestFocus()
                //println("Not an Email")
                return@setOnClickListener
            }
            auth.sendPasswordResetEmail(binding.Email.text.toString().trim())
            Toast.makeText(
                this.context,
                "Please Check Your Email to Reset Your Password!",
                Toast.LENGTH_SHORT
            ).show()


        }
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


}