package com.example.loginimplementation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loginimplementation.Fragments.CategoriesFragment
import com.example.loginimplementation.Fragments.HistoryFragments
import com.example.loginimplementation.Fragments.HomeFragment
import com.example.loginimplementation.Adapter.DatabaseHelper
import com.example.loginimplementation.databinding.LoggedActivityBinding
import com.example.loginimplementation.Adapter.ViewPageAdapter
import com.example.loginimplementation.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser




class LoggedInActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logged_activity)


        //create Database
        val context = this
        val db = DatabaseHelper(context)

        //we want to define initial categories when starting the project
        val category = arrayOf(
            "Food","Drinks", "Electronics", "Education", "Health", "Laundry", "Advertisement", "Beauty",
            "Sport"
        )
        for (item in category){
            db.insertcat(item)
        }

        setUpTabs()
    }



    // Helps to set up all the Tabs and Fragments we use
    private fun setUpTabs() {

        //Declare the fragments we have in the main page
        val adapter = ViewPageAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), "Home")
        adapter.addFragment(CategoriesFragment(), "Categories")
        adapter.addFragment(HistoryFragments(), "History")

        //Assign all the buttons and tools from xml page
        val viewPager= findViewById<androidx.viewpager.widget.ViewPager>(R.id.viewPager)
        val bar2 = findViewById<com.google.android.material.tabs.TabLayout>(R.id.bar2)
        //val bar1 = findViewById<com.google.android.material.tabs.TabLayout>(R.id.bar1)

        //Have an adapter for the fragment slide and assign it to one bar
        viewPager.adapter = adapter
        bar2.setupWithViewPager(viewPager)

        //set up icones for all fragments
        bar2.getTabAt(0)!!.setIcon(R.drawable.ic_home_24px)
        bar2.getTabAt(1)!!.setIcon(R.drawable.ic_category_24px)
        bar2.getTabAt(2)!!.setIcon(R.drawable.ic_history_24px)

    }

}

