package com.example.loginimplenetation

import android.content.res.Resources
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.loginimplenetation.databinding.SettingsActivityBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.loginimplenetation.databinding.ProfileFragmentBinding
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.profile_fragment.*


class SettingsActivity: AppCompatActivity(){

    private lateinit var binding: SettingsActivityBinding
    private lateinit var view: View
    private var light = true

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = SettingsActivityBinding.inflate(layoutInflater)
        view = binding.root
        if(!light){
            setTheme(R.style.Dark)
        }else
            setTheme(R.style.Light)
        setContentView(view)





        displaySettings()

    }


    fun displaySettings(){

        val bntBack = binding.retur
        bntBack.setOnClickListener {
            finish()
        }
        val btnEditProfile = binding.profileSettings
        btnEditProfile.setOnClickListener {
            setContentView(R.layout.profile_fragment)
        }
        val swtDarkmode = binding.darkMode
        swtDarkmode.setOnClickListener{
            if(light){
                setTheme(R.style.AppThemeDark)
                light = !light
                recreate()
            }else {
                setTheme(R.style.Light)
                light = !light
                recreate()
            }

        }

    }
}

class ProfileFragment: Fragment(){
    private lateinit var binding: ProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(layoutInflater)
        display()
        return inflater.inflate(R.layout.profile_fragment,container, false)
    }

    private fun display(){
        val back = binding.cancel
        back.setOnClickListener {
            returnTransition
        }
        val submit = binding.submit
        submit.setOnClickListener{
            val fname = binding.fName.text.toString().trim()
            val lname = binding.lName.text.toString().trim()
            val budget = binding.budget.text.toString().trim()
            val housesize = binding.people.text.toString().trim()
            var budgetperiod = "null as String"


            if(TextUtils.isEmpty(fname)){
                binding.fName.error = "You need to provide your name"
                binding.fName.requestFocus()
            }
            if(TextUtils.isEmpty(lname)){
                binding.lName.error = "You need to provide your last name"
                binding.lName.requestFocus()
            }
            if(TextUtils.isEmpty(budget)){
                binding.budget.error = "You need to provide a budget"
                binding.budget.requestFocus()
            }
            if(TextUtils.isEmpty(housesize)){
                binding.people.error = "How many people are you shopping for?"
                binding.people.requestFocus()
            }
            if(binding.weekly.isChecked)
                budgetperiod = "weekly"
            else if(binding.daily.isChecked)
                budgetperiod = "daily"
            else if(binding.monthly.isChecked)
                budgetperiod = "monthly"
            else{
                binding.radiocheck.error = "You need to select a budget period"
                binding.radiocheck.requestFocus()
            }


        }


        //this is where the information provided would get ported over to the DB for storage
            }

        }








