package com.example.loginimplenetation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loginimplenetation.databinding.CameraAccessActivityBinding

class CameraAccessActivity :AppCompatActivity(){

    private lateinit var binding: CameraAccessActivityBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = CameraAccessActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        display()
    }



    private fun display(){



    }
}