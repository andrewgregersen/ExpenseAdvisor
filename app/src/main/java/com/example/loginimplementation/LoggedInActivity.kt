package com.example.loginimplementation

import android.os.Bundle
import android.widget.Toast
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
import com.google.firebase.database.FirebaseDatabase
import java.io.File
import java.io.InputStream


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

        //Notification area



        if(db.getNotification().size != 0){

            var not= db.getProfile()
            //Toast.makeText(context, "You have set your notification to be: " + not.toString(), Toast.LENGTH_SHORT).show()

            val temp = db.getNotification()
            //Toast.makeText(context, temp.toString(), Toast.LENGTH_SHORT).show()

        }












//        val desc= "You have reach 75 % in your Food categorie"
//        val desc2 = "You have reached 90% in your food categorie"
//
//        db.insertNotification(1, desc)
//        db.insertNotification(1, desc2)

        setUpTabs()
        //fillDataBase()
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

    private fun fillDataBase(){

        var ref = FirebaseDatabase.getInstance().getReference("Items")


        var bufferedReader = applicationContext.assets.open("Food.txt").bufferedReader()
        var lineList = ArrayList<String>()

        bufferedReader.useLines {  lines -> lines.forEach { lineList.add(it) } }

        for(i in lineList) {
            var item = Item(i, "Food")
            ref.child(item.name.trim()).setValue(item).addOnCompleteListener {
                Toast.makeText(applicationContext, it.toString() + " added in Food", Toast.LENGTH_LONG).show()
            }
        }

        var bufferedReader2 = applicationContext.assets.open("Advertisement.txt").bufferedReader()
        lineList = ArrayList<String>()

        bufferedReader2.useLines {  lines -> lines.forEach { lineList.add(it) } }

        for(i in lineList) {
            var item = Item(i.toString(), "Advertisement")
            ref.child(item.name.trim()).setValue(item).addOnCompleteListener {
                Toast.makeText(applicationContext, it.toString() + " added in Food", Toast.LENGTH_LONG).show()
            }
        }
//
//
//        var bufferedReader3 = applicationContext.assets.open("Beauty.txt").bufferedReader()
//        lineList = ArrayList<String>()
//
//        bufferedReader3.useLines {  lines -> lines.forEach { lineList.add(it) } }
//
//        for(i in lineList) {
//
//            var item = Item(i.toString(), "Beauty")
//            ref.child(item.name.trim()).setValue(item).addOnCompleteListener {
//                Toast.makeText(applicationContext, it.toString() + " added in Food", Toast.LENGTH_LONG).show()
//            }
//        }
//
        var bufferedReader4 = applicationContext.assets.open("Education.txt").bufferedReader()
        lineList = ArrayList<String>()

        bufferedReader4.useLines {  lines -> lines.forEach { lineList.add(it) } }

        for(i in lineList) {

            var item = Item(i.toString(), "Education")
            ref.child(item.name.trim()).setValue(item).addOnCompleteListener {
                Toast.makeText(applicationContext, it.toString() + " added in Food", Toast.LENGTH_LONG).show()
            }
        }


        var bufferedReader5 = applicationContext.assets.open("Health.txt").bufferedReader()
        lineList = ArrayList<String>()

        bufferedReader5.useLines {  lines -> lines.forEach { lineList.add(it) } }

        for(i in lineList) {

            var item = Item(i.toString(), "Health")
            ref.child(item.name.trim()).setValue(item).addOnCompleteListener {
                Toast.makeText(applicationContext, it.toString() + " added in Food", Toast.LENGTH_LONG).show()
            }
        }


        var bufferedReader6 = applicationContext.assets.open("Electronics.txt").bufferedReader()
        lineList = ArrayList<String>()

        bufferedReader6.useLines {  lines -> lines.forEach { lineList.add(it) } }

        for(i in lineList) {

            var item = Item(i.toString(), "Electronics")
            ref.child(item.name.trim()).setValue(item).addOnCompleteListener {
                Toast.makeText(applicationContext, it.toString() + " added in Food", Toast.LENGTH_LONG).show()
            }
        }



        var bufferedReader7 = applicationContext.assets.open("Laundry.txt").bufferedReader()
        lineList = ArrayList<String>()

        bufferedReader7.useLines {  lines -> lines.forEach { lineList.add(it) } }

        for(i in lineList) {

            var item = Item( i.toString(), "Laundry")
            ref.child(item.name.trim()).setValue(item).addOnCompleteListener {
                Toast.makeText(applicationContext, it.toString() + " added in Food", Toast.LENGTH_LONG).show()
            }
        }





    }

}

