package com.example.loginimplementation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class SettingsActivity: AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)



        toolbar = findViewById(R.id.toolbar)
        //setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

//        var toogle= ActionBarDrawerToggle{
//            this, drawerLayout, toolbar, 0, 0
//        }

        val toogle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)

        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()
        navView.setNavigationItemSelectedListener(this)
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
            }
            R.id.nav_notification -> {
                Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show()
                val newAct = Intent(applicationContext, Notification::class.java)
                startActivity(newAct)
            }
            R.id.nav_faq -> {
                Toast.makeText(this, "FAQ", Toast.LENGTH_SHORT).show()

            }
            R.id.nav_update -> {
                Toast.makeText(this, "Update Profile", Toast.LENGTH_SHORT).show()
                val newAct = Intent(applicationContext, MyProfile::class.java)
                startActivity(newAct)


            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sign Out", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}










