package com.example.loginimplenetation.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.loginimplenetation.Confirm_Update_Item
import com.example.loginimplenetation.R
import com.example.loginimplenetation.databinding.ItemLayoutBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class RecyclerView_Adapter(private var titles: List<String>, private var details: List<String>, private var images:List<Int>):
RecyclerView.Adapter<CustomViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val v= LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(v,parent,false)
        return CustomViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val binding = holder.binding as ItemLayoutBinding
        binding.historyTitle.text = titles[position]
        binding.historyDescription.text= details[position]
        binding.historyImage.setImageResource(images[position])

        binding.deleteItem.setOnClickListener { v: View ->
            val position: Int= position
            //when click on delete, ask for confirmation
            //showAlertDialog(v)

            MaterialAlertDialogBuilder(binding.historyTitle.context)
                .setTitle("Warning!")
                .setMessage("Do you want to delete this item from the category? ")
                .setNegativeButton("No"){ _, _ ->
                    Toast.makeText(binding.historyTitle.context, "Cancel ", Toast.LENGTH_SHORT).show()

                }
                .setPositiveButton("Yes"){ _, _ ->

                    val db = DatabaseHelper(binding.historyTitle.context)
                    val item:String = binding.historyTitle.text.toString()
                    db.deleteItem(item)

                    notifyItemRemoved(position)
                }
                .show()

        }

        binding.editItem.setOnClickListener {v:View ->


            val intent = Intent(v.context, Confirm_Update_Item::class.java)

            var bundle = Bundle()
            bundle.putString("ItemName", binding.historyTitle.text.toString())
            bundle.putString("ItemPrice", binding.historyDescription.text.toString())

            Toast.makeText(v.context, ""+  binding.historyTitle.text.toString() + " " + binding.historyDescription.text.toString() , Toast.LENGTH_LONG).show()
//

            val db= DatabaseHelper(v.context)
            // bundle.putString("ItemCategory", db.getCategoryName(db.getCategoryID(itemTitle!!.text.toString())))

            intent.putExtras(bundle)
            v.context.startActivity(intent)

        }
    }


    override fun getItemCount(): Int {
        return titles.size
    }



    //Create a function that show an alert when deleting an item
    fun showAlertDialog(itemView: View){

        MaterialAlertDialogBuilder(itemView.context)
            .setTitle("Warning!")
            .setMessage("Do you want to delete this item from the category? ")
            .setNegativeButton("No"){ _, _ ->
                Toast.makeText(itemView.context, "Cancel ", Toast.LENGTH_SHORT).show()

            }
            .setPositiveButton("Yes"){ _, _ ->
                val itemTitle: TextView= itemView.findViewById(R.id.history_title)
                val db = DatabaseHelper(itemView.context)
                val item:String = itemTitle.text.toString()
                db.deleteItem(item)
            }
            .show()
    }


}

open class CustomViewHolder(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root)