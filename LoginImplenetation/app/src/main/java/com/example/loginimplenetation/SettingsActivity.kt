package com.example.loginimplenetation

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.loginimplenetation.databinding.SettingsActivityBinding
import kotlinx.android.synthetic.*


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

        val bntBack = binding.retur
        bntBack.setOnClickListener {
            finish()
        }
        val btnEditProfile = binding.profileSettings
        btnEditProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        val swtDarkmode = binding.darkMode
        swtDarkmode.setOnClickListener{
            if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_NO){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            //need to write in DB store functionality around here to make note of users choice.
        }

    }
}










