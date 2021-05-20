package com.example.loginimplenetation.itemOfCategory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginimplenetation.adapter.DatabaseHelper
import com.example.loginimplenetation.adapter.RecyclerView_Adapter
import com.example.loginimplenetation.R


class OtherItem : AppCompatActivity() {
    private var titlesList= mutableListOf<String>()
    private var descList= mutableListOf<String>()
    private var imageList = mutableListOf<Int>()

    val context = this
    val db = DatabaseHelper(context)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_layout_fulllist)

        //postToList()

        titlesList = db.getItemsOfCategory("Other")
        descList= db.getOnlyPriceOfItemOfCategory("Other")
        postToList(titlesList.size) //get enough drawable for all items

        var rv_recycleView= findViewById<RecyclerView>(R.id.rv_recycleView)
        rv_recycleView?.layoutManager = LinearLayoutManager(this)
        rv_recycleView?.adapter = RecyclerView_Adapter(titlesList, descList, imageList)

    }

    private fun addToList(image: Int){
        imageList.add(image)
    }

    //sample for test
    private fun postToList(x: Int){
        for(i in 1..x){
            addToList(R.drawable.other)
        }
    }
}