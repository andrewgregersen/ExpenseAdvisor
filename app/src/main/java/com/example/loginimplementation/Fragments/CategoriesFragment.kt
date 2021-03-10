package com.example.loginimplementation.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.example.loginimplenetation.R
import com.example.myapplication.ItemOfCategory.*
import com.example.myapplication.list_view_model_file
import kotlinx.android.synthetic.main.fragment_categories.*


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

    //function that populate the categories and their image (drawable)
    private  fun fun_PopulateList(): ArrayList<list_view_model_file>{
        val list= java.util.ArrayList<list_view_model_file>()

        for(i in 0..catImage.size){
            val imageModel= list_view_model_file()
            imageModel.setImage(catImage[i])
            imageModel.setNames(categories[i])
            list.add(imageModel)
        }

        return list

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

        food.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                requireActivity().run {
                    startActivity(Intent(this, FoodItems::class.java))
                    true
                }
            }
        })

        electronics.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                requireActivity().run {
                    startActivity(Intent(this, ElectronicsItem::class.java))
                    true
                }
            }
        })

        education.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                requireActivity().run {
                    startActivity(Intent(this, EducationItem::class.java))
                    true
                }
            }
        })

        health.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                requireActivity().run {
                    startActivity(Intent(this, HealthItem::class.java))
                    true
                }
            }
        })

        laundry.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                requireActivity().run {
                    startActivity(Intent(this, LaundryItem::class.java))
                    true
                }
            }
        })

        advertisement.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                requireActivity().run {
                    startActivity(Intent(this, AdvertisementItem::class.java))
                    true
                }
            }
        })

        beauty.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                requireActivity().run {
                    startActivity(Intent(this, BeautyItem::class.java))
                    true
                }
            }
        })

        others.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                requireActivity().run {
                    startActivity(Intent(this, OtherItem::class.java))
                    true
                }
            }
        })


        // Inflate the layout for this fragment
        return view
    }


}