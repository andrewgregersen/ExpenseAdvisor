package com.example.loginimplenetation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.example.loginimplenetation.itemOfCategory.*
import com.example.loginimplenetation.list_view_model_file
import com.example.loginimplenetation.R


class CategoriesFragment : Fragment() {

    val context = this

    // Declare all drawable (images) in a array
    private var catImage= intArrayOf(
        R.drawable.food,
            R.drawable.electronics, R.drawable.education, R.drawable.health,
            R.drawable.laundry, R.drawable.advertisement, R.drawable.beauty,
            R.drawable.other)

    // declare all categories
    private var categories = arrayOf("Food",
            "Electronics",
            "Education",
            "Health",
            "Laundry",
            "Advertisement",
            "Beauty",
            "Other")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //start with this in a fragment
        val view: View = inflater!!.inflate(R.layout.fragment_categories, container, false)

        // declare all the variables categories
        var food = view.findViewById<RelativeLayout>(R.id.food)
        var electronics = view.findViewById<RelativeLayout>(R.id.electronics)
        var education = view.findViewById<RelativeLayout>(R.id.education)
        var health = view.findViewById<RelativeLayout>(R.id.health)
        var laundry = view.findViewById<RelativeLayout>(R.id.laundry)
        var advertisement = view.findViewById<RelativeLayout>(R.id.advertisement)
        var beauty = view.findViewById<RelativeLayout>(R.id.beauty)
        var others = view.findViewById<RelativeLayout>(R.id.other)


       //redirect each category to its activity

        food.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, FoodItems::class.java))
                true
            }
        }

        electronics.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, ElectronicsItem::class.java))
                true
            }
        }

        education.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, EducationItem::class.java))
                true
            }
        }

        health.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, HealthItem::class.java))
                true
            }
        }

        laundry.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, LaundryItem::class.java))
                true
            }
        }

        advertisement.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, AdvertisementItem::class.java))
                true
            }
        }

        beauty.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, BeautyItem::class.java))
                true
            }
        }

        others.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, OtherItem::class.java))
                true
            }
        }


        // Inflate the layout for this fragment
        return view
    }


}