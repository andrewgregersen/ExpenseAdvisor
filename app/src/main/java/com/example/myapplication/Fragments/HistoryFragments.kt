package com.example.myapplication.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.RecyclerView_Adapter
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_manual_entry.*
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragments : Fragment() {

    private var titlesList= mutableListOf<String>()
    private var descList= mutableListOf<String>()
    private var imageList = mutableListOf<Int>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
         postToList()

        //itemName = findViewById(R.id.idItemName) as EditText
 //THE PROBLEME IS HERE


        rv_recyclerView?.layoutManager = LinearLayoutManager(this.requireContext())
        rv_recyclerView?.adapter = RecyclerView_Adapter(titlesList, descList, imageList)

    }


    private fun addToList(title: String, description: String, image: Int){
        titlesList.add(title)
        descList.add(description)
        imageList.add(image)
    }

    //sample for test
    private fun postToList(){
        for(i in 1..25){
            addToList("Title $i", "Description $i", R.mipmap.ic_launcher_round)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        postToList()
        val view: View = inflater!!.inflate(R.layout.fragment_history, container, false)
//        rv_recyclerView?.layoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
//        rv_recyclerView?.adapter = RecyclerView_Adapter(titlesList, descList, imageList)



        //  private var listView: ListView? = null
        //       listView= view?.findViewById(R.id.listView_cat) as ListView


                rv_recyclerView?.layoutManager = LinearLayoutManager(this.requireContext())
                rv_recyclerView?.adapter = RecyclerView_Adapter(titlesList, descList, imageList)


        // Inflate the layout for this fragment
        return view
    }


}