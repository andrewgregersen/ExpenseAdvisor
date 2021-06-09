package com.example.loginimplementation.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.example.loginimplementation.*
import com.example.loginimplementation.Adapter.DatabaseHelper
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment()  {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    // Here we can define the PieChart
    override fun onCreateView(
            inflater: LayoutInflater,
            @Nullable container: ViewGroup?,
            @Nullable savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

    //--------------------------------------------------------------------------------------------//
      // Work with pie chart in the Home fragment


        //create Database and get all categories from it to assign in the PieChart
        val db = DatabaseHelper(this.requireContext())  /* ALWAYS DECLARE IT IN THE VIEWS */
        var category= arrayListOf<String>()

        /**** WE GET THE CATEGORIES FROM THE DATABASE ****/
        category = db.getCategories() as ArrayList<String>

        var quantity = arrayListOf<Float>(35.2f, 10.4f, 28.99f, 3.5f, 70.1f, 30.4f, 9.2f)

        //Populating a list of PieEntries
        val pieEntries: MutableList<PieEntry> = ArrayList<PieEntry>()
        for (i in quantity.indices) {
            pieEntries.add(PieEntry(quantity[i], category[i]))
        }

        var user = db.isUser()
        var profil =  db.isProfil()
        if (!user){
            db.createUser()
            Toast.makeText( this.context, "User is getting create", Toast.LENGTH_LONG).show()
            print("User is getting create")
        }
        else{
            Toast.makeText( this.context, "User exist already", Toast.LENGTH_LONG).show()
            print("User exist already")
        }

        if (!profil){
            db.createProfil()
            Toast.makeText( this.context, "Profil is getting create", Toast.LENGTH_LONG).show()
            print("Profil is getting create")
        }
        else{
            Toast.makeText( this.context, "Profil exist already", Toast.LENGTH_LONG).show()
            print("Profil exist already")
        }





        // set a dataset and build a pie object
        val dataSet = PieDataSet(pieEntries, "Expenses")
        val data = PieData(dataSet)

        // Get the chart, Let call the pieChart from the xml file
        val chart = view.findViewById<View>(R.id.chart) as PieChart

        //change color, dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        dataSet?.setColors(*ColorTemplate.COLORFUL_COLORS)
        chart.setData(data)

        //set animation
        chart.animateY(2000)
        chart.invalidate()

     //-------------------------------------------------------------------------//

        //Declare variable for buttons

        val signout = view?.findViewById<Button>(R.id.signout)
        val settings= view?.findViewById<Button>(R.id.settings)
        val addReceipt= view?.findViewById<Button>(R.id.addReceipt)


        signout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            activity!!.finish()
        }

        //work with settings
        settings?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(context, SettingsActivity::class.java);
                startActivity(intent)
            }
        })

        //work with adding a receipt either from camera or manually
        addReceipt?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                // create a popupMenu when cliking on add button
                val popupMenu = PopupMenu(context, v)
                popupMenu.setOnMenuItemClickListener { item ->
                    when(item.itemId){
                        //when selecting Camera and Gallery
                        R.id.menu_CameraGallery ->{
                            val intent = Intent(context, CameraAccessActivity::class.java);
                            startActivity(intent)
                            true
                        }
                        // When selecting manual entry
                        R.id.menu_Manual ->{
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
                }catch (e:Exception){
                    Log.e("Main", "Error showing menu icons.", e)
                } finally {
                    //Show the popup menu
                    popupMenu.show()
                }
            }
        })

        //Notification icon
        val notification = db.getNotificationNumber()
        var belt: ImageView = view?.findViewById(R.id.belt)
        var amount: TextView = view?.findViewById(R.id.number)
        // Set the number of notification to appear next to the belt
        amount.setText(notification.toString())

        //Make the belt and the number of notification clickable
        //They are link to the Notification activity
        amount?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(context, Notification::class.java);
                startActivity(intent)
            }
        })

        belt?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(context, Notification::class.java);
                startActivity(intent)
            }
        })


        return view
    }
}



