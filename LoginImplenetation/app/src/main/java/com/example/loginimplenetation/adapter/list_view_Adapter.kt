package com.example.loginimplenetation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.loginimplenetation.list_view_model_file
import com.example.loginimplenetation.R

class list_view_Adapter(private val context: Context,
                        private val ListViewArrayList: ArrayList<list_view_model_file>): BaseAdapter(){




    override fun getCount(): Int {
        return ListViewArrayList.size
    }

    override fun getViewTypeCount(): Int{
        return count
    }

    override fun getItemViewType(position: Int): Int{
        return position
    }

    override fun getItem(position: Int): Any {
        return ListViewArrayList[position]
    }

    override fun getItemId(position: Int): Long {
       return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView= convertView
        val holder: ViewHolder

        if(itemView == null) {
            holder = ViewHolder()
            val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            itemView = inflater.inflate(R.layout.list_view_model, null, true)

            holder.name = itemView!!.findViewById(R.id.Iname) as TextView
            holder.iv = itemView.findViewById(R.id.imgView) as ImageView

            itemView.tag = holder
        }else{
            holder = itemView.tag as ViewHolder
        }

        holder.name!!.setText(ListViewArrayList[position].getNames())
        holder.iv!!.setImageResource(ListViewArrayList[position].getImage())

        ///Item Clicked Action
        holder.name!!.setOnClickListener(){ v: View ->
            Toast.makeText(v.getContext(), "Cliked on item", Toast.LENGTH_SHORT).show();
        }

        return itemView
    }

    private inner class ViewHolder{
        var name: TextView? = null
        internal var iv: ImageView? = null
    }


}