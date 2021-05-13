package com.example.loginimplenetation.adapter

import android.content.Context
import android.content.Intent
import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginimplenetation.ActivityReceiptUpdate
import com.example.loginimplenetation.R


class ReceiptAdapter(context: Context, arrayList: ArrayList<Int>, dates: ArrayList<String>, prices: ArrayList<String>) : RecyclerView.Adapter<ReceiptAdapter.ViewHolderClass>() {
    var context: Context = context
    var arrayList: ArrayList<Int> = arrayList
    var dates: ArrayList<String> = dates
    var prices: ArrayList<String> = prices

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.history_layout,parent, false)
        val viewHolderClass = ViewHolderClass(view);
        return viewHolderClass;
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val viewHolderClass = holder as ViewHolderClass
        viewHolderClass.textview.text = "Receipt id: " + arrayList[position]
        viewHolderClass.date.text = "Date: " + dates[position];
        viewHolderClass.total.text = "Total prices: $" + prices[position]




    }
    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var context: Context? = null
        var textview: TextView=  itemView.findViewById(R.id.hhistory_title) as TextView
        var date: TextView=  itemView.findViewById(R.id.hhistory_description) as TextView
        var total: TextView=  itemView.findViewById(R.id.hhistory_price) as TextView

        init {
           // textview = itemView.findViewById(R.id.hhistory_title) as TextView
            var receiptItem = itemView.findViewById(R.id.hhistory_description) as TextView
        }

//
//            val intent = Intent(itemView.context,ActivityReceiptUpdate::class.java)
//            intent.action = Intent.ACTION_GET_CONTENT
//            intent.putExtra("Receipt ID",arrayList[adapterPosition])
//        }


    }


}