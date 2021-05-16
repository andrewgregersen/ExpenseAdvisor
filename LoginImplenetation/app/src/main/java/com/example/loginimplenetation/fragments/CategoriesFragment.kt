package com.example.loginimplenetation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.loginimplenetation.R
import com.example.loginimplenetation.databinding.FragmentCategoriesBinding
import com.example.loginimplenetation.itemOfCategory.*


class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //start with this in a fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_categories,container,false)




       //redirect each category to its activity

        binding.food.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, FoodItems::class.java))
            }
        }

        binding.electronics.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, ElectronicsItem::class.java))
            }
        }

        binding.education.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, EducationItem::class.java))
            }
        }

        binding.health.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, HealthItem::class.java))
            }
        }

        binding.laundry.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, LaundryItem::class.java))
            }
        }

        binding.advertisement.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, AdvertisementItem::class.java))
            }
        }

        binding.beauty.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, BeautyItem::class.java))
            }
        }

        binding.other.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, OtherItem::class.java))
            }
        }


        // Inflate the layout for this fragment
        return view
    }


}