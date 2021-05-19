package com.example.loginimplenetation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginimplenetation.adapter.ReceiptAdapter
import com.example.loginimplenetation.adapter.DatabaseHelper
import com.example.loginimplenetation.R
import com.example.loginimplenetation.databinding.FragmentHistoryBinding

class HistoryFragments : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_history,container,false)

        val db = DatabaseHelper(this.requireContext())



        val myAdapter = ReceiptAdapter(requireContext(), db.getAll_Receipt_ID() as ArrayList,
            db.getAll_Date_Receipt_ID() as ArrayList, db.getAll_Total_Receipt_ID() as ArrayList)
        binding.historyRecycleView.layoutManager = LinearLayoutManager(context)
        binding.historyRecycleView.adapter = myAdapter

        return binding.root
    }
}