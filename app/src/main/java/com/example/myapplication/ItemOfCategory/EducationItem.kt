package com.example.myapplication.ItemOfCategory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.DatabaseHelper
import com.example.myapplication.Adapter.RecyclerView_Adapter
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_history.*

class EducationItem : AppCompatActivity() {
    private var titlesList= mutableListOf<String>()
    private var descList= mutableListOf<String>()
    private var imageList = mutableListOf<Int>()

    val context = this
    val db = DatabaseHelper(context)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_layout_fulllist)

        //postToList()

        titlesList = db.getItemsOfCategory("Education")
        descList= db.getOnlyPriceOfItemOfCategory("Education")
        postToList(titlesList.size) //get enough drawable for all items

        rv_recyclerView?.layoutManager = LinearLayoutManager(this)
        rv_recyclerView?.adapter = RecyclerView_Adapter(titlesList, descList, imageList)

    }

    private fun addToList(image: Int){
        imageList.add(image)
    }

    //sample for test
    private fun postToList(x: Int){
        for(i in 1..x){
            addToList(R.drawable.education)
        }
    }}