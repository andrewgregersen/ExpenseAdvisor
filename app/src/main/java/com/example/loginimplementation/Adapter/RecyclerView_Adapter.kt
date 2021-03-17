package com.example.loginimplementation.Adapter

import android.R.attr.name
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.loginimplementation.Adapter.DatabaseHelper
import com.example.loginimplementation.Confirm_Update_Item
import com.example.loginimplementation.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class RecyclerView_Adapter(private var titles: List<String>, private var details: List<String>, private var images:List<Int>):
RecyclerView.Adapter<RecyclerView_Adapter.ViewHolder>(){


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val itemTitle: TextView= itemView.findViewById(R.id.history_title)
        val itemDetail: TextView= itemView.findViewById(R.id.history_description)
        val itemPicture: ImageView= itemView.findViewById(R.id.historyImage)
        val deleteItem: ImageView= itemView.findViewById(R.id.deleteItem)
        val editItem: ImageView= itemView.findViewById(R.id.editItem)



        init {
            deleteItem.setOnClickListener { v: View ->
                val position: Int= adapterPosition
                //when click on delete, ask for confirmation
                //showAlertDialog(v)

                MaterialAlertDialogBuilder(itemView.getContext())
                        .setTitle("Warning!")
                        .setMessage("Do you want to delete this item from the category? ")
                        .setNegativeButton("No"){dialog, which ->
                            Toast.makeText(itemView.context, "Cancel ", Toast.LENGTH_SHORT).show()

                        }
                        .setPositiveButton("Yes"){dialog, which ->

                            val db = DatabaseHelper(itemView.getContext())
                            val item:String = itemTitle!!.text.toString()
                            db.deleteItem(item)

                            notifyItemRemoved(position)
                        }
                        .show()

            }

            editItem.setOnClickListener {v:View ->


                val intent = Intent(v.context, Confirm_Update_Item::class.java)

                var bundle = Bundle()
                bundle.putString("ItemName", itemTitle!!.text.toString())
                bundle.putString("ItemPrice", itemDetail!!.text.toString())

                Toast.makeText(v.getContext(), ""+  itemTitle!!.text.toString() + " " + itemDetail!!.text.toString() , Toast.LENGTH_LONG).show()
//

                val db= DatabaseHelper(v.context)
               // bundle.putString("ItemCategory", db.getCategoryName(db.getCategoryID(itemTitle!!.text.toString())))

                intent.putExtras(bundle)
                v.context.startActivity(intent)

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



    //Create a function that show an alerte when deleting an item
    fun showAlertDialog(itemView: View){

        MaterialAlertDialogBuilder(itemView.getContext())
            .setTitle("Warning!")
            .setMessage("Do you want to delete this item from the category? ")
            .setNegativeButton("No"){dialog, which ->
                Toast.makeText(itemView.context, "Cancel ", Toast.LENGTH_SHORT).show()

            }
            .setPositiveButton("Yes"){dialog, which ->
                val itemTitle: TextView= itemView.findViewById(R.id.history_title)
                val db = DatabaseHelper(itemView.getContext())
                val item:String = itemTitle!!.text.toString()
                db.deleteItem(item)
            }
            .show()
    }
}