package com.example.loginimplementation.model

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.loginimplementation.R
import com.example.loginimplementation.databinding.AutofillItemBindingImpl
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class Autofill_Adapter(private val autofillList:List<Autofill_Data>):RecyclerView.Adapter<Autofill_Adapter.AutofillViewHolder>() {
    // automatic generated from autofill_item.xml
    inner class AutofillViewHolder(val v: AutofillItemBindingImpl):RecyclerView.ViewHolder(v.root){
        val img= v.root.findViewById<ImageView>(R.id.autofilIm)
        val deleteItem: ImageView = itemView.rootView.findViewById(R.id.autodelete)

        init {
            img.setOnClickListener {
                Toast.makeText(v.root.context, " clicker image", Toast.LENGTH_SHORT).show()
            }

            //val context: Context = ContextThemeWrapper(v.root.getContext(), R.style.LightTheme)
            val context: Context = ContextThemeWrapper(v.root.getContext(), R.style.AppTheme2)
            val dialogBuilder = MaterialAlertDialogBuilder(context)
            deleteItem.setOnClickListener {
                Toast.makeText(context, " clicker ", Toast.LENGTH_SHORT).show()
                val position: Int= adapterPosition
                //when click on delete, ask for confirmation
                //showAlertDialog(v)

                MaterialAlertDialogBuilder(context)
                        .setTitle("Warning!")
                        .setMessage("Do you want to delete this item from the category? ")
                        .setNegativeButton("No"){dialog, which ->
                            Toast.makeText(context, "Cancel ", Toast.LENGTH_SHORT).show()
                        }
                        .setPositiveButton("Yes"){dialog, which ->
                            autofillList.drop(adapterPosition)
                            notifyItemRemoved(this.getLayoutPosition())
                            //notifyItemRemoved(adapterPosition)
                        }.show()
            }
        }
    }

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

