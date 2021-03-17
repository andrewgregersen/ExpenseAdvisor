package com.example.loginimplementation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loginimplementation.Adapter.DatabaseHelper


class ShowProfileActivity: AppCompatActivity() {

    private lateinit var DataBase: DatabaseHelper
    //private lateinit var binding : ShowProfileActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ShowProfileActivityBinding.inflate(layoutInflater)
        DataBase = DatabaseHelper(this)


    }
}