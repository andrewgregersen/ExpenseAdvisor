package com.example.loginimplementation.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.loginimplementation.*
import com.example.loginimplementation.Adapter.DatabaseHelper
import com.example.loginimplementation.databinding.FragmentHomeBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment()  {
    private lateinit var binding: FragmentHomeBinding

    // Here we can define the PieChart
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)

        updateChart()

        //-------------------------------------------------------------------------//

        //Declare variable for buttons


        //implement signoutButton
        binding.signout.setOnClickListener {
            val auth = FirebaseAuth.getInstance()
            auth.signOut()
            activity?.finish()
        }

// DEPRECATED
        //work with settings
//        binding.settings.setOnClickListener {
//            val intent = Intent(context, SettingsActivity::class.java);
//            startActivity(intent)
//        }

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

        var db= DatabaseHelper(requireContext())
        //create a user
        var user = db.isUser()
        if (!user){
            db.createUser()
            Toast.makeText( this.context, "User is getting create", Toast.LENGTH_LONG).show()
            print("User is getting create")
        }
        else{
            Toast.makeText( this.context, "User exist already", Toast.LENGTH_LONG).show()
            print("User exist already")
        }


        //Matching Notifications and profiles
        val compare = db.getDetailProfiles() as ArrayList
        val quantity = db.getCatTotalCost() as ArrayList
        Toast.makeText(this.requireContext(), compare.toString(), Toast.LENGTH_LONG).show()
        if(compare.size != 0){
            for (i in 0 until compare.size step 2){

                var cat = compare.get(i).toString()
                var limit = compare.get(i+1).toDouble()

                if(cat.equals("Food")){
                    sendNotification(1, quantity.get(0),limit,"Food")
                }
                else if(cat.equals("Electronics")){
                    sendNotification(3, quantity.get(2),limit,"Electronics")
                }
                else if(cat.equals("Education")){
                    sendNotification(4, quantity.get(3),limit,"Education")
                }
                else if(cat.equals("Health")){
                    sendNotification(5, quantity.get(4),limit,"Health")
                }
                else if(cat.equals("Laundry")){
                    sendNotification(6, quantity.get(5),limit,"Laundry")
                }
                else if(cat.equals("Advertisement")){
                    sendNotification(7, quantity.get(6),limit,"Advertisement")
                }
                else if(cat.equals("Beauty")){
                    sendNotification(8, quantity.get(7),limit,"Beauty")
                }
                else{
                    continue
                }

            }

        }

        return binding.root
    }

    fun sendNotification(id: Int, value: Double, limit: Double, name: String) {
        val db = DatabaseHelper(requireContext())
        if (value / 2 < limit && limit < (value * 3) / 4) {
            val desc = "You have reach 50 % in your " + name + " categorie"
            db.insertNotification(id, desc)
        }

        if ((value * 3) / 4 < limit && limit < value) {
            val desc = "You have reach 75 % in your " + name + " categorie"
            db.insertNotification(id, desc)
        }

        if (value < limit) {
            val desc = "You passed the limit in your " + name + " categorie"
            db.insertNotification(id, desc)
        }
    }

    private fun updateChart() {
        //--------------------------------------------------------------------------------------------//
        // Work with pie chart in the Home fragment


        //create Database and get all categories from it to assign in the PieChart
        val db = DatabaseHelper(this.requireContext())  /* ALWAYS DECLARE IT IN THE VIEWS */

        /**** WE GET THE CATEGORIES FROM THE DATABASE ****/
        val category = db.getCategories() as ArrayList

        val quantity = db.getCatTotalCost() as ArrayList


        //Populating a list of PieEntries
        val pieEntries: MutableList<PieEntry> = ArrayList()

        for (x in 0..8) {
            if (quantity[x].toFloat() != 0.0F) {
                pieEntries.add(PieEntry(quantity[x].toFloat(), category[x]))
            }
        }
        if (pieEntries.size != 0) {
            binding.textView2.visibility = View.INVISIBLE
        } else {
            binding.textView2.visibility = View.VISIBLE
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
    }

    override fun onResume() {
        super.onResume()
        updateChart()
    }

    override fun onPause() {
        super.onPause()
        updateChart()
    }

}

