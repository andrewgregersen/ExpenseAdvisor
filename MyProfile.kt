package com.example.loginimplementation

import android.app.Notification
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.*
import com.example.loginimplementation.Adapter.DatabaseHelper

class MyProfile : AppCompatActivity() {
    // these are the global variables
    var classSpinner: Spinner? = null
    var divSpinner: Spinner? = null
    var notificationSpinner: Spinner? = null
    var amountText: EditText? = null



    var category = ""
    var notification = ""
    var amount = ""
    var selectedClass= ""
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)
        classSpinner = findViewById<View>(R.id.classSpinner) as Spinner?
        divSpinner = findViewById<View>(R.id.divSpinner2) as Spinner?
        notificationSpinner = findViewById<View>(R.id.spinner3) as Spinner?
        amountText = findViewById<EditText>(R.id.amount)

        // Class Spinner implementing onItemSelectedListener
        classSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedClass = parent.getItemAtPosition(position).toString()
                when (selectedClass) {
                    "Class 1" ->                         // assigning div item list defined in XML to the div Spinner
                        divSpinner?.adapter = ArrayAdapter(this@MyProfile,
                                android.R.layout.simple_spinner_dropdown_item,
                                resources.getStringArray(R.array.array_data))

                    "Class 2" -> divSpinner?.adapter = ArrayAdapter(this@MyProfile,
                            android.R.layout.simple_spinner_dropdown_item,
                            resources.getStringArray(R.array.array_data2))

                    "Class 3" -> divSpinner?.adapter = ArrayAdapter(this@MyProfile,
                            android.R.layout.simple_spinner_dropdown_item,
                            resources.getStringArray(R.array.array_data3))

                }


                //set divSpinner Visibility to Visible
                divSpinner?.visibility = View.VISIBLE
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        // Div Spinner implementing onItemSelectedListener
        divSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                category = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // can leave this empty
            }
        }

        notificationSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                notification = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // can leave this empty
            }
        }


        var submit = findViewById<Button>(R.id.Submitbutton)
        submit.setOnClickListener {
            amount = amountText?.text.toString()
            var db = DatabaseHelper(this.applicationContext)
            db.editProfil(selectedClass.toString(), amount.toDouble(), category.toString())


            Toast.makeText( this@MyProfile, """Class: 	${selectedClass.toString()} Div: 	${category.toString()}  Notification: ${notification.toString()}  Amount: $amount""", Toast.LENGTH_LONG).show()

        }
    }
}