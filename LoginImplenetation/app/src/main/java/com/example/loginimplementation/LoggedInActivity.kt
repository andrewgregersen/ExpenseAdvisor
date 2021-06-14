package com.example.loginimplementation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.example.loginimplementation.Adapter.DatabaseHelper
import com.example.loginimplementation.Adapter.ViewPageAdapter
import com.example.loginimplementation.Fragments.CategoriesFragment
import com.example.loginimplementation.Fragments.HistoryFragments
import com.example.loginimplementation.Fragments.HomeFragment
import com.example.loginimplementation.Fragments.NotificationFragment
import com.example.loginimplementation.databinding.ContentMainBinding
import com.example.loginimplementation.databinding.LoggedActivityBinding
import com.example.loginimplementation.databinding.SettingsActivityBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoggedInActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var mUser: FirebaseUser? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: LoggedActivityBinding
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    private lateinit var cMBinding: ContentMainBinding
    private lateinit var binding2: LoggedActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        mUser = FirebaseAuth.getInstance().currentUser
        binding = DataBindingUtil.setContentView(this, R.layout.logged_activity)
        binding2= LoggedActivityBinding.inflate(layoutInflater)
        cMBinding = ContentMainBinding.inflate(layoutInflater)

        //create Database
        val context = this
        val db = DatabaseHelper(context)
        db.initCat()

       //setUp all the Fragments
        setUpTabs()

        //Setup the Setting DrawerLayout
        toolbar = cMBinding.toolbar
        drawerLayout = binding.settingsDrawer
        navView = binding.navView

        val toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    private fun setUpTabs() {

        //Declare the fragments we have in the main page
        val adapter = ViewPageAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), "Home")
        adapter.addFragment(CategoriesFragment(), "Categories")
        adapter.addFragment(HistoryFragments(), "History")
        adapter.addFragment(NotificationFragment(), "Notification")

        //Assign all the buttons and tools from xml page
        val viewPager = findViewById<androidx.viewpager.widget.ViewPager>(binding.viewPager.id)
        val bar2 = findViewById<com.google.android.material.tabs.TabLayout>(binding.bar2.id)
        //val bar1 = findViewById<com.google.android.material.tabs.TabLayout>(R.id.bar1)

        //Have an adapter for the fragment slide and assign it to one bar
        viewPager.adapter = adapter
        bar2.setupWithViewPager(viewPager)

        //set up icones for all fragments
        bar2.getTabAt(0)!!.setIcon(R.drawable.ic_home_24px)
        bar2.getTabAt(1)!!.setIcon(R.drawable.ic_category_24px)
        bar2.getTabAt(2)!!.setIcon(R.drawable.ic_history_24px)
        bar2.getTabAt(3)!!.setIcon(R.drawable.ic_not)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                val newAct = Intent(applicationContext, ProfileShow::class.java)
                startActivity(newAct)
            }
            R.id.nav_addreceipt -> {
                Toast.makeText(this, "Add Receipt", Toast.LENGTH_SHORT).show()
                val newAct = Intent(applicationContext, ManualEntry::class.java)
                startActivity(newAct)
            }
            R.id.nav_notification -> {
                Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show()
                val newAct = Intent(applicationContext, Notification::class.java)
                startActivity(newAct)
            }
            R.id.nav_faq -> {
                Toast.makeText(this, "FAQ", Toast.LENGTH_SHORT).show()
                val newAct = Intent(applicationContext, faq::class.java)
                startActivity(newAct)

            }
            R.id.nav_update -> {
                Toast.makeText(this, "Update Profile", Toast.LENGTH_SHORT).show()
                val newAct = Intent(applicationContext, MyProfile::class.java)
                startActivity(newAct)


            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sign Out", Toast.LENGTH_SHORT).show()
                val auth = FirebaseAuth.getInstance()
                auth.signOut()
                this?.finish()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
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