package com.example.loginimplementation.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginimplementation.ActivityReceiptUpdate
import com.example.loginimplementation.Adapter.DatabaseHelper
import com.example.loginimplementation.R
import com.example.loginimplementation.databinding.FragmentHistoryBinding
import com.example.loginimplementation.databinding.HistoryLayoutBinding

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


    class ReceiptAdapter(val context: Context, val arrayList: ArrayList<Int>, val dates: ArrayList<String>, val prices: ArrayList<String>) : RecyclerView.Adapter<CustomViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val vh = LayoutInflater.from(parent.context)
            val binding = HistoryLayoutBinding.inflate(vh,parent,false)
            return CustomViewHolder(binding)
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            val binding = holder.binding as HistoryLayoutBinding
            ("Receipt id: " + arrayList[position]).also { binding.hhistoryTitle.text = it }
            ("Date: " + dates[position]).also { binding.hhistoryDescription.text = it };
            "Total prices:  $${prices[position]}".also { binding.hhistoryPrice.text = it }

            binding.hcvCardView.setOnClickListener {
                val intent = Intent(context, ActivityReceiptUpdate::class.java)
                intent.putExtra("ReceiptID",arrayList[position])
                context.startActivity(intent)
            }
        }
        override fun getItemCount(): Int {
            return arrayList.size
        }



    }

    open class CustomViewHolder(val binding: HistoryLayoutBinding): RecyclerView.ViewHolder(binding.root)
}