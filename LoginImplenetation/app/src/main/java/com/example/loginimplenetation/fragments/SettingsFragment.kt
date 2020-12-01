package com.example.loginimplenetation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.loginimplenetation.databinding.SettingsActivityBinding
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.loginimplenetation.ProfileActivity
import com.example.loginimplenetation.R
import com.example.loginimplenetation.databinding.ContentMainBinding
import com.example.loginimplenetation.databinding.LoggedActivityBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth


class SettingsFragment: Fragment() , NavigationView.OnNavigationItemSelectedListener{

    //initializes the databinding for objects to make object lookup more efficent
    private lateinit var binding: LoggedActivityBinding
    private lateinit var cMBinding: ContentMainBinding
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        //inflate the bindings
        binding = LoggedActivityBinding.inflate(layoutInflater)
        cMBinding = ContentMainBinding.inflate(layoutInflater)
        val view = inflater.inflate(R.layout.settings_activity, container, false)

        //initialized the other lateinit vars
        toolbar = cMBinding.toolbar
        drawerLayout = binding.drawerLayout
        navView = binding.navView



        val toggle = ActionBarDrawerToggle(activity,drawerLayout,toolbar,0,0)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        //depricated from testing
        //displaySettings()

        return view

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(activity, "Profile", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_addreceipt -> {
                Toast.makeText(activity, "Add Receipt", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_notification -> {
                Toast.makeText(activity, "Notification", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_faq -> {
                Toast.makeText(activity, "FAQ", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_update -> {
                val intent = Intent(activity, ProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_logout -> {
                
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

/*

depricated testing code

    fun displaySettings(){




        val bntBack = binding.retur
        bntBack.setOnClickListener {
            finish()
        }
        val btnEditProfile = binding.profileSettings
        btnEditProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        val swtDarkmode = binding.darkMode
        swtDarkmode.setOnClickListener{
            if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_NO){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            //need to write in DB store functionality around here to make note of users choice.
        }




    }

*/

}










