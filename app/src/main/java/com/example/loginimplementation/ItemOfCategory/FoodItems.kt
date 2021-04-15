package com.example.loginimplementation.ItemOfCategory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginimplementation.Adapter.DatabaseHelper
import com.example.loginimplementation.R
import com.example.loginimplementation.Adapter.RecyclerView_Adapter
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.item_layout_fulllist.*
import java.security.AccessController.getContext

class FoodItems : AppCompatActivity() {

    private var titlesList= mutableListOf<String>()
    private var descList= mutableListOf<String>()
    private var imageList = mutableListOf<Int>()

    val context = this
    val db = DatabaseHelper(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_layout_fulllist)

        //postToList()
        titlesList = db.getItemsOfCategory("Food")
        descList= db.getOnlyPriceOfItemOfCategory("Food")
        postToList(titlesList.size) //get enough drawable for all items



        var rv_recyclerView= findViewById<RecyclerView>(R.id.rv_recycleView)
        rv_recyclerView?.layoutManager = LinearLayoutManager(this)
        rv_recyclerView?.adapter = RecyclerView_Adapter(titlesList, descList, imageList)
    }

    private fun addToList(image: Int){
        imageList.add(image)
    }

    //sample for test
    private fun postToList(x: Int){
        for(i in 1..x){
            addToList(R.drawable.food)
        }
    }
}