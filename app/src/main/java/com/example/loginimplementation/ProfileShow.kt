package com.example.loginimplementation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.loginimplementation.Adapter.DatabaseHelper

class ProfileShow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_show)

        val listView = findViewById<ListView>(R.id.listView)

        val context = this
        val db = DatabaseHelper(context)
        var profiles= db.getAllProfiles()

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this,
        android.R.layout.simple_list_item_1, profiles)

        listView.adapter= arrayAdapter

    }
}