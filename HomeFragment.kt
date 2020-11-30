package com.example.mainpage.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.example.mainpage.Camera_Activity
import com.example.mainpage.R
import com.example.mainpage.SettingsActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*


class HomeFragment : Fragment() {




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

        //define the categories and value (we later get there from the data base and can make them clickable)
        val category = arrayOf(
            "Food",
            "Electronics",
            "Education",
            "Health",
            "Rent",
            "Advertisement",
            "Beauty"
        )
        val quantity = floatArrayOf(35.2f, 10.4f, 28.99f, 3.5f, 70.1f, 30.4f, 9.2f)

        //Populating a list of PieEntries
        val pieEntries: MutableList<PieEntry> = ArrayList<PieEntry>()
        for (i in quantity.indices) {
            pieEntries.add(PieEntry(quantity[i], category[i]))
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


        //work with settings
        settings?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                val intent= Intent(context, SettingsActivity::class.java);
                startActivity(intent)
            }
        })

        //work with camera
        addReceipt?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                val intent2= Intent(context, Camera_Activity::class.java);
                startActivity(intent2)
            }
        })

        return view
    }
}



