package com.example.loginimplenetation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loginimplenetation.adapter.DatabaseHelper

class ShowProfileActivity: AppCompatActivity() {

    private lateinit var DataBase: DatabaseHelper
    //private lateinit var binding : ShowProfileActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ShowProfileActivityBinding.inflate(layoutInflater)
        DataBase = DatabaseHelper(this)


    }
}