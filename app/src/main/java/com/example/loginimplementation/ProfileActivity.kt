package com.example.loginimplenetation

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.loginimplementation.Adapter.DatabaseHelper
import com.example.loginimplenetation.databinding.ProfileActivityBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity: AppCompatActivity(){


        private lateinit var binding: ProfileActivityBinding
        private lateinit var view: View

        override fun onCreate(SavedInstanceState: Bundle?) {
            super.onCreate(SavedInstanceState)

            binding = ProfileActivityBinding.inflate(layoutInflater)
            view = binding.root
            setContentView(view)

            display()

        }

    private fun display() {
            val back = binding.cancel
            back.setOnClickListener {
                finish()
            }
            val submit = binding.submit
            submit.setOnClickListener {
                val fname = binding.fName.text.toString().trim()
                val lname = binding.lName.text.toString().trim()
                val budget = binding.budget.text.toString().trim()
                val housesize = binding.people.text.toString().trim()
                var budgetperiod = "null as String"


                if (TextUtils.isEmpty(fname)) {
                    binding.fName.error = "You need to provide your name"
                    binding.fName.requestFocus()
                }
                if (TextUtils.isEmpty(lname)) {
                    binding.lName.error = "You need to provide your last name"
                    binding.lName.requestFocus()
                }
                if (TextUtils.isEmpty(budget)) {
                    binding.budget.error = "You need to provide a budget"
                    binding.budget.requestFocus()
                }
                if (TextUtils.isEmpty(housesize)) {
                    binding.people.error = "How many people are you shopping for?"
                    binding.people.requestFocus()
                }
                if (binding.weekly.isChecked)
                    budgetperiod = "weekly"
                else if (binding.daily.isChecked)
                    budgetperiod = "daily"
                else if (binding.monthly.isChecked)
                    budgetperiod = "monthly"
                else {
                    binding.radiocheck.error = "You need to select a budget period"
                    binding.radiocheck.requestFocus()
                }

                //
                val DB = DatabaseHelper(this)
                //FirebaseAuth.getInstance().currentUser?.let { it1 -> DB.insertprofile(budget.toDouble(), fname, lname,housesize.toInt(), it1.uid,budgetperiod) }





            }


        }


}