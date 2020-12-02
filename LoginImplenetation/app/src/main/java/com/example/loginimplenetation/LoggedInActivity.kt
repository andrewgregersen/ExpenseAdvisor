package com.example.loginimplenetation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loginimplenetation.databinding.LoggedActivityBinding
import com.example.loginimplenetation.fragments.CategoriesFragment
import com.example.loginimplenetation.fragments.HistoryFragments
import com.example.loginimplenetation.fragments.HomeFragment
import com.example.mainpage.Adapter.ViewPageAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser




class LoggedInActivity: AppCompatActivity(){

    private var mUser : FirebaseUser? = null
    private lateinit var auth : FirebaseAuth
    private lateinit var binding : LoggedActivityBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        mUser = FirebaseAuth.getInstance().currentUser
        binding = LoggedActivityBinding.inflate(layoutInflater)
        setContentView(R.layout.logged_activity)

       setUpTabs()
    }

    private fun setUpTabs(){

        //Declare the fragments we have in the main page
        val adapter = ViewPageAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), "Home")
        adapter.addFragment(CategoriesFragment(), "Categories")
        adapter.addFragment(HistoryFragments(), "History")

        //Assign all the buttons and tools from xml page
        val viewPager= findViewById<androidx.viewpager.widget.ViewPager>(binding.viewPager.id)
        val bar2 = findViewById<com.google.android.material.tabs.TabLayout>(binding.bar2.id)
        //val bar1 = findViewById<com.google.android.material.tabs.TabLayout>(R.id.bar1)

        //Have an adapter for the fragment slide and assign it to one bar
        viewPager.adapter = adapter
        bar2.setupWithViewPager(viewPager)

        //set up icones for all fragments
        bar2.getTabAt(0)!!.setIcon(R.drawable.ic_home_24px)
        bar2.getTabAt(1)!!.setIcon(R.drawable.ic_category_24px)
        bar2.getTabAt(2)!!.setIcon(R.drawable.ic_history_24px)
    }



    /*

    //displays the vairous buttons and graphs on the screen
    private fun displayStuff() {


        This is old depricated code, kept in for testing reasons

        val btnsignout = findViewById<Button>(binding.signOut.id)
        btnsignout.setOnClickListener {
            logOut()
        }
        val txtnamehere = findViewById<TextView>(binding.putName.id)
        txtnamehere.text = auth.currentUser?.email
        val btnPhoto = findViewById<Button>(binding.Photos.id)
        btnPhoto.setOnClickListener {
            val intent = Intent(this@LoggedInActivity, CameraAccessActivity::class.java)
            startActivity(intent)
        }
        val btnSettings =findViewById<Button>(binding.Settings.id)
        btnSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        //access the database where the individual receipts are stored
        val bntDatabase = findViewById<Button>(binding.database.id)
        bntDatabase.setOnClickListener {
           // val intent = Intent(this,DatabaseActivity::class.java)
            //startActivity(intent)
            val message = findViewById<TextView>(binding.message.id)
            message.text = R.string.DBAccess.toString()
        }




    }
         */



    //logs the user out of the application









}

