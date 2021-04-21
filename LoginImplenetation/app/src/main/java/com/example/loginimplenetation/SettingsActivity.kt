package com.example.loginimplenetation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.loginimplentation.R
import com.example.loginimplentation.databinding.ContentMainBinding
import com.example.loginimplentation.databinding.SettingsActivityBinding
import com.google.android.material.navigation.NavigationView


class SettingsActivity: AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    //initializes the databinding for objects to make object lookup more efficent
    private lateinit var binding: SettingsActivityBinding
    private lateinit var cMBinding: ContentMainBinding
    private lateinit var view: View
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        //inflate the bindings
        binding = SettingsActivityBinding.inflate(layoutInflater)
        cMBinding = ContentMainBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)


        //initalized the back button
        val backBTN = findViewById<Button>(R.id.goBack2)
        backBTN.setOnClickListener {
            finish()
        }



        //initialized the other lateinit vars
        toolbar = cMBinding.toolbar
        drawerLayout = binding.drawerLayout
        navView = binding.navView



        val toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        //depricated from testing
        //displaySettings()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_addreceipt -> {
                Toast.makeText(this, "Add Receipt", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_notification -> {
                Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_faq -> {
                Toast.makeText(this, "FAQ", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_darkmode -> {
                if(theme.equals(R.style.Light)){
                    setTheme(R.style.Dark)
                }else
                    setTheme(R.style.Light)
                recreate()
            }
            R.id.nav_update -> {
                intent = Intent(this,ProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Would sign you out if this were on the home page", Toast.LENGTH_SHORT).show()
                /* can be implemented once moved to main screen
                auth.signOut()
                finish()

                 */
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










