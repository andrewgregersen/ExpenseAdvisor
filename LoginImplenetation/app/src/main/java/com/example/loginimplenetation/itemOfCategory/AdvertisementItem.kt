package com.example.loginimplenetation.itemOfCategory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginimplenetation.adapter.RecyclerView_Adapter
import com.example.loginimplenetation.R
import com.example.loginimplenetation.adapter.DatabaseHelper
import com.example.loginimplenetation.databinding.ItemLayoutFulllistBinding

class AdvertisementItem : AppCompatActivity() {
    private var titlesList= mutableListOf<String>()
    private var descList= mutableListOf<String>()
    private var imageList = mutableListOf<Int>()

    val context = this
    val db = DatabaseHelper(context)


    private lateinit var binding: ItemLayoutFulllistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.item_layout_fulllist)

        //postToList()

        titlesList = db.getItemsOfCategory("Advertisement")
        descList= db.getOnlyPriceOfItemOfCategory("Advertisement")
        postToList(titlesList.size) //get enough drawable for all items

        binding.rvRecycleView.layoutManager = LinearLayoutManager(this)
        binding.rvRecycleView.adapter = RecyclerView_Adapter(titlesList, descList, imageList)

    }

    private fun addToList(image: Int){
        imageList.add(image)
    }

    //sample for test
    private fun postToList(x: Int){
        for(i in 1..x){
            addToList(R.drawable.advertisement)
        }
    }
}