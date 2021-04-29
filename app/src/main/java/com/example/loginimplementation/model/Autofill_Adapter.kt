package com.example.loginimplementation.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.loginimplementation.R
import com.example.loginimplementation.databinding.AutofillItemBindingImpl

class Autofill_Adapter(private val autofillList:List<Autofill_Data>):RecyclerView.Adapter<Autofill_Adapter.AutofillViewHolder>() {
    // automatic generated from autofill_item.xml
    inner class AutofillViewHolder(val v: AutofillItemBindingImpl):RecyclerView.ViewHolder(v.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutofillViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<AutofillItemBindingImpl>(inflater, R.layout.autofill_item,parent,false)
        return AutofillViewHolder(v)
    }

    override fun getItemCount(): Int {
        return autofillList.size
    }

    override fun onBindViewHolder(holder: AutofillViewHolder, position: Int) {
        val autolist = autofillList[position]
        holder.v.isAutofillList = autolist
        //holder.v.autofilIm.setImageResource(autolist.autoImage)

    }

}

