package com.example.loginimplenetation.fragments

import android.content.Intent
import android.database.DatabaseUtils
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.loginimplenetation.adapter.DatabaseHelper
import com.example.loginimplenetation.ManualEntry
import com.example.loginimplenetation.CameraAccessActivity
import com.example.loginimplenetation.SettingsActivity
import com.example.loginimplenetation.R
import com.example.loginimplenetation.databinding.FragmentHomeBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.FirebaseAuth
import kotlin.collections.ArrayList


class HomeFragment : Fragment()  {

    private lateinit var binding: FragmentHomeBinding

    // Here we can define the PieChart
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_home,container,false)

    //--------------------------------------------------------------------------------------------//
      // Work with pie chart in the Home fragment


        //create Database and get all categories from it to assign in the PieChart
        val db = DatabaseHelper(this.requireContext())  /* ALWAYS DECLARE IT IN THE VIEWS */

        /**** WE GET THE CATEGORIES FROM THE DATABASE ****/
        val category = db.getCategories() as ArrayList<String>

        val quantity = db.getCatTotalCost() as ArrayList


        //Populating a list of PieEntries
        val pieEntries: MutableList<PieEntry> = ArrayList()
        for (x in 0..8) {
            pieEntries.add(PieEntry(quantity[x].toFloat(), category[x]))
        }


        //db.getCategoryID("Food")



        // set a dataset and build a pie object
        val dataSet = PieDataSet(pieEntries, "Expenses")
        val data = PieData(dataSet)


        //change color, dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        binding.chart.data = data

        //set animation
        binding.chart.animateY(2000)
        binding.chart.invalidate()

     //-------------------------------------------------------------------------//

        //Declare variable for buttons



        //implement signoutButton
        binding.signout.setOnClickListener {
            val auth = FirebaseAuth.getInstance()
            auth.signOut()
            activity?.finish()
        }


        //work with settings
        binding.settings.setOnClickListener {
            val intent = Intent(context, SettingsActivity::class.java);
            startActivity(intent)
        }

        //work with adding a receipt either from camera or manually
        binding.addReceipt.setOnClickListener { v ->
            // create a popupMenu when cliking on add button
            val popupMenu = PopupMenu(context, v)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    //when selecting Camera and Gallery
                    R.id.menu_CameraGallery -> {
                        val intent = Intent(context, CameraAccessActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    // When selecting manual entry
                    R.id.menu_Manual -> {
                        val intent = Intent(context, ManualEntry::class.java);
                        startActivity(intent)
                        true
                    }
                    else -> false


                }
            }

            popupMenu.inflate(R.menu.menu_add_choise)

            // add icons in our popupMenu
            try {
                val fieldMPopup = popupMenu::class.java.getDeclaredField("mPopup")
                fieldMPopup.isAccessible = true
                val mPopup = fieldMPopup.get(popupMenu)
                mPopup.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
            } catch (e: Exception) {
                Log.e("Main", "Error showing menu icons.", e)
            } finally {
                //Show the popup menu
                popupMenu.show()
            }
        }

        return binding.root
    }
}



