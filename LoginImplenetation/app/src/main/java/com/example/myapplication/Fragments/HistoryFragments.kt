package com.example.myapplication.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.*
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.DatabaseHelper
import com.example.myapplication.Adapter.ReceiptAdapter
import com.example.myapplication.Adapter.RecyclerView_Adapter
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_manual_entry.*
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragments : Fragment() {

//    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//
//        var text: TextView= itemView.findViewById(R.id.hhistory_title)
//        var description: TextView= itemView.findViewById(R.id.hhistory_description)
//        var edit: ImageView= itemView.findViewById(R.id.heditItem)
//        var delete: ImageView= itemView.findViewById(R.id.hdeleteItem)
//        var historyRecyclerView: RecyclerView= itemView.findViewById(R.id.historyRecycleView)
//
//
//        fun udapteEvent(){
//            text.text= "Maman"
//            description.text= "Papa Jean"
//
//
//
//
//        }
//
//    }

//    inner class HistoryAdapter(val items: List<String>, val title: List<String> )
//        :RecyclerView.Adapter<HistoryFragments.ViewHolder>(){
//
//
//
//
//
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//            val view= LayoutInflater.from(parent.context).inflate(R.layout.history_layout, parent, false)
//            return ViewHolder(view)
//        }
//
//        override fun getItemCount(): Int {
//            return items.size
//        }
//
//        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            holder.text.text = title[position]
//            holder.description.text= items[position]
//            holder.udapteEvent()
//
//        }
//
//
//    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val list= listOf("a", "b", "c")
//        val list2= listOf("1","2","3")
//
//        historyRecycleView?.hasFixedSize()
//        historyRecycleView?.layoutManager= LinearLayoutManager(context)
//        historyRecycleView?.itemAnimator= DefaultItemAnimator()
//        historyRecycleView?.adapter= HistoryAdapter(list,list2)
        val db = DatabaseHelper(this.requireContext())

        //discribe the array to pass to the recycler view
        val id = db.getAll_Receipt_ID() as ArrayList<Int>
        var total= db.getAll_Total_Receipt_ID() as ArrayList<String>
        val date= db.getAll_Date_Receipt_ID() as ArrayList<String>


        var mylist= arrayListOf("Mango", "Banana", "Yep", "Avocadot", "Bujumbura") as ArrayList<String>


        var view = inflater.inflate(R.layout.fragment_history, container, false)
        var historyRecycleView = view.findViewById<RecyclerView>(R.id.historyRecycleView)
        //var Myadapter = list?.let { getContext()?.let { it1 -> ReceiptAdapter(it1, it) } }
        var Myadapter= ReceiptAdapter(requireContext(), id, date, total)
        //var linearLayoutManager = LinearLayoutManager(getContext())
        historyRecycleView.layoutManager= LinearLayoutManager(getContext())
        historyRecycleView.adapter = Myadapter

        return view
    }


}