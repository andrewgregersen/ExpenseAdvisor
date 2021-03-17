package com.example.loginimplementation.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginimplementation.Adapter.ReceiptAdapter
import com.example.loginimplementation.MainActivity
import com.example.loginimplementation.Adapter.DatabaseHelper
import com.example.loginimplementation.R
import kotlinx.android.synthetic.main.activity_manual_entry.*
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragments : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val db = DatabaseHelper(this.requireContext())

        //discribe the array to pass to the recycler view
        val id = db.getAll_Receipt_ID() as ArrayList<Int>
        var total= db.getAll_Total_Receipt_ID() as ArrayList<String>
        val date= db.getAll_Date_Receipt_ID() as ArrayList<String>

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