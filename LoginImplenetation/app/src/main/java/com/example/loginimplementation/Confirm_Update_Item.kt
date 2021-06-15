package com.example.loginimplementation

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils.substring
import android.view.LayoutInflater
import android.widget.*
import com.example.loginimplementation.R
import com.example.loginimplementation.Fragments.CategoriesFragment
import com.example.loginimplementation.Adapter.DatabaseHelper
import com.example.loginimplementation.ItemOfCategory.*
import kotlinx.android.synthetic.main.confirm_update_item.*
import kotlinx.android.synthetic.main.confirm_update_item.view.*
import java.security.AccessController.getContext

class Confirm_Update_Item : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.confirm_update_item)

        val context= applicationContext
        val db = DatabaseHelper(context)


        var changeItemName= findViewById<EditText>(R.id.changeItemName)
        var changeItemPrice= findViewById<EditText>(R.id.changeItemPrice)
        var showChangedCategory=findViewById<EditText>(R.id.showChangedCategory)
        var changeCategory= findViewById<Button>(R.id.changeCategory)

        //get the previous item title and price
        var bundle= intent.extras

        var item= bundle?.getString("ItemName") as String
        var price= bundle?.getString("ItemPrice") as String

        //delete the $ signe
        price= substring(price,1, price.length)


        changeItemName.setText(item)
        changeItemPrice.setText(price)
        showChangedCategory.setText(db.getCategoryName(db.getItemID(item)))


        //Toast.makeText(context, ""+ changeCategory?.text.toString() + " " + changeItemName?.text.toString() + " " + showChangedCategory?.text.toString(), Toast.LENGTH_LONG).show()

        //Set up the selection for the choice of category
        changeCategory.setOnClickListener {
            var popup = PopupMenu(context, changeCategory)
            popup.inflate(R.menu.menu_categorie_manual)
            popup.setOnMenuItemClickListener {
                //get the choice from categories and display it on the text view
                val choice = (it.title).toString()//show dialog
                showChangedCategory.setText(choice)
                // Toast.makeText(this, choice, Toast.LENGTH_SHORT).show()
                true
            }//Button click
            //Display the list of categories
            popup.show()
        }


        //set up submit button
         submitChange.setOnClickListener {

            val title= changeItemName.text.toString()
            val p= changeItemPrice.text.toString()
            val price= p.toInt()
            val category= showChangedCategory.text.toString()

            //first get the item id of the element we woud like to edit
            val id= db.getItemID(item)
            //call the update function
            //Toast.makeText(context, ""+ title + " " + price.toString()+ " " + category, Toast.LENGTH_LONG).show()


             db.updateItem(title, price, category, id )
             transfert(category)


        }

        cancelChange.setOnClickListener {
            val redirect:String =db.getCategoryName(db.getItemID(item))
            transfert(redirect)
        }



    }

    fun transfert(act: String){

        if(act == "Food"){
            startActivity(Intent(this, FoodItems::class.java))
        }

        if(act == "Advertisement"){
            startActivity(Intent(this, AdvertisementItem::class.java))
        }

        if(act == "Beauty"){
            startActivity(Intent(this, BeautyItem::class.java))
        }

        if(act == "Education"){
            startActivity(Intent(this, EducationItem::class.java))
        }

        if(act == "Electronics"){
            startActivity(Intent(this, ElectronicsItem::class.java))
        }

        if(act == "Health"){
            startActivity(Intent(this, HealthItem::class.java))
        }

        if(act == "Laundry"){
            startActivity(Intent(this, LaundryItem::class.java))
        }

        if(act == "Other"){
            startActivity(Intent(this, OtherItem::class.java))
        }

    }
}