package com.example.loginimplenetation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.loginimplenetation.databinding.SettingsActivityBinding

class SettingsActivity: AppCompatActivity(){


    private lateinit var binding: SettingsActivityBinding
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = SettingsActivityBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)



        displaySettings()

    }


    fun displaySettings(){

    }
}