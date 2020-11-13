package com.example.loginimplenetation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loginimplenetation.databinding.ForgottenActivityBinding

class ForgottenActivity: AppCompatActivity() {

    private lateinit var binding : ForgottenActivityBinding


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ForgottenActivityBinding.inflate(layoutInflater)
        setContentView(R.layout.forgotten_activity)
    }
}