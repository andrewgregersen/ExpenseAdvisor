package com.example.loginimplenetation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.loginimplenetation.databinding.SettingsActivityBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.loginimplenetation.databinding.ProfileFragmentBinding


class SettingsActivity: AppCompatActivity(){

    private lateinit var binding: SettingsActivityBinding
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = SettingsActivityBinding.inflate(layoutInflater)
        view = binding.root
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
        back.setOnClickListener{
            onDestroyView()
        }
        val submit = binding.submit
        submit.setOnClickListener{
            addInfoToDB()
        }
    }

    private fun addInfoToDB(){
        val fname = binding.fName.text.toString().trim()
        val lname = binding.lName.text.toString().trim()
        val budget = binding.budget.text.trim()
        val periodid = when{
            binding.daily.id == (binding.radiogroup.checkedRadioButtonId) -> binding.daily
            binding.weekly.id == (binding.radiogroup.checkedRadioButtonId) -> binding.weekly
            binding.monthly.id == binding.radiogroup.checkedRadioButtonId -> binding.monthly
            else -> {
                binding.radiocheck.error = "You need to select a budget period!"
                binding.radiocheck.requestFocus()
            }
        }

        //this is where the information provided would get ported over to the DB for storage

    }


}