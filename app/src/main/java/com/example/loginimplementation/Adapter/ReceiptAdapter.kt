package com.example.loginimplementation.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginimplementation.R
import com.example.loginimplementation.Receipt
import com.example.loginimplementation.databinding.AutofillItemBindingImpl


class ReceiptAdapter(context: Context, arrayList: ArrayList<Int>, dates: ArrayList<String>, prices: ArrayList<String>) : RecyclerView.Adapter<ReceiptAdapter.ViewHolderClass>() {
    var context: Context
    var arrayList: ArrayList<Int>
    var dates: ArrayList<String>
    var prices: ArrayList<String>


    init {
        this.context = context
        this.arrayList = arrayList
        this.dates= dates
        this.prices= prices

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        var view: View = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_layout,parent, false)
        var viewHolderClass: ViewHolderClass = ViewHolderClass(view);
        return viewHolderClass;
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val viewHolderClass = holder as ViewHolderClass
        viewHolderClass.textview.setText("Receipt id: " + arrayList[position])
        viewHolderClass.date.setText("Date: " + dates[position]);
        viewHolderClass.total.setText("Total prices: $" + prices[position])

    }
    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var context: Context? = null
        var textview: TextView=  itemView.findViewById(R.id.hhistory_title) as TextView
        var date: TextView=  itemView.findViewById(R.id.hhistory_description) as TextView
        var total: TextView=  itemView.findViewById(R.id.hhistory_price) as TextView

        init {
           // textview = itemView.findViewById(R.id.hhistory_title) as TextView
            var receiptItem = itemView.findViewById(R.id.hhistory_description) as TextView
        }
    }


}