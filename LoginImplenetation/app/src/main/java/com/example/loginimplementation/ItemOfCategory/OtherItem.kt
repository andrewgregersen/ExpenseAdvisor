package com.example.loginimplementation.ItemOfCategory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginimplementation.Adapter.DatabaseHelper
import com.example.loginimplementation.R
import com.example.loginimplementation.Adapter.RecyclerView_Adapter
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.item_layout_fulllist.*

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