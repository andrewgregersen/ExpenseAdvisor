package com.example.mainpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.mainpage.Adapter.DatabaseHelper
import com.example.mainpage.Adapter.ViewPageAdapter
import com.example.mainpage.Fragments.CategoriesFragment
import com.example.mainpage.Fragments.HistoryFragments
import com.example.mainpage.Fragments.HomeFragment
import com.github.mikephil.charting.charts.PieChart
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //create Database
        val context = this
        val db = DatabaseHelper(context)

        //testing our database with some functions
//        db.insertcat()
//        db.insertbelong()
//        db.insertcont()
//        db.insertnotification()
//        db.insertprofil()


        //Call the function that create the PieChart
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