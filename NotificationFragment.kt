package com.example.loginimplementation.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.loginimplementation.Adapter.DatabaseHelper
import com.example.loginimplementation.R

class NotificationFragment: Fragment() {


    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_notification, container, false)
        listView = view.findViewById<ListView>(R.id.NotifList)

        val context = this
        val db = DatabaseHelper(this.requireContext())
        var notifications = db.getNotification()

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            this.requireContext(),
            android.R.layout.simple_list_item_1, notifications
        )

        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { adapterView, view, position, id ->
            Toast.makeText(getContext(), " Read and will be deleted ", Toast.LENGTH_LONG).show()
            db.deleteNotification(notifications[position])
            arrayAdapter.setNotifyOnChange(true)
        }

        return view
    }

}