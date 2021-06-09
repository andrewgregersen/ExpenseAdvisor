package com.example.loginimplementation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.loginimplementation.Adapter.DatabaseHelper

class Notification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val listView = findViewById<ListView>(R.id.notList)

        val context = this
        val db = DatabaseHelper(context)
        var notifications= db.getNotification()

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, notifications)

        listView.adapter= arrayAdapter

        listView.setOnItemClickListener { adapterView, view, position, id ->
            Toast.makeText(this, " Read and will be deleted ", Toast.LENGTH_LONG).show()
            db.deleteNotification(notifications[position])
            arrayAdapter.setNotifyOnChange(true)
        }
    }
}