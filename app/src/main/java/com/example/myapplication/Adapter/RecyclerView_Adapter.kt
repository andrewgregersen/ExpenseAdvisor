package com.example.myapplication.Adapter

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Adapter.DatabaseHelper
import com.example.myapplication.MainActivity
import kotlinx.android.synthetic.main.item_layout.*

class RecyclerView_Adapter(private var titles: List<String>, private var details: List<String>, private var images:List<Int>):
RecyclerView.Adapter<RecyclerView_Adapter.ViewHolder>(){





    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val itemTitle: TextView= itemView.findViewById(R.id.history_title)
        val itemDetail: TextView= itemView.findViewById(R.id.history_description)
        val itemPicture: ImageView= itemView.findViewById(R.id.historyImage)

        init {
            itemView.setOnClickListener { v: View ->
                val position: Int= adapterPosition

                val db = DatabaseHelper(itemView.getContext())
                val item:String = itemTitle!!.text.toString()
                db.deleteItem(item)
                Toast.makeText(itemView.context, "You have delete the item "+ item, Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        //cte iemlayout before
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDetail.text= details[position]
        holder.itemPicture.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return titles.size
    }


}