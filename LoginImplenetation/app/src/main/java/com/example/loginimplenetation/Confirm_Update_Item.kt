package com.example.loginimplenetation

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.substring
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.loginimplenetation.adapter.DatabaseHelper
import com.example.loginimplenetation.databinding.ConfirmUpdateItemBinding
import com.example.loginimplenetation.itemOfCategory.*

class Confirm_Update_Item : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ConfirmUpdateItemBinding>(
            this,
            R.layout.confirm_update_item
        )


        val db = DatabaseHelper(binding.root.context)


        //get the previous item title and price
        var bundle = intent.extras

        var item = bundle?.getString("ItemName") as String
        var price = bundle.getString("ItemPrice") as String

        //delete the $ signe
        price = substring(price, 1, price.length)


        binding.changeItemName.setText(item)
        binding.changeItemPrice.setText(price)
        binding.showChangedCategory.setText(db.getCategoryName(db.getItemID(item)))


        //Toast.makeText(context, ""+ changeCategory?.text.toString() + " " + changeItemName?.text.toString() + " " + showChangedCategory?.text.toString(), Toast.LENGTH_LONG).show()

        //Set up the selection for the choice of category
        binding.changeCategory.setOnClickListener {
            var popup = PopupMenu(binding.root.context, binding.changeCategory)
            popup.inflate(R.menu.menu_categorie_manual)
            popup.setOnMenuItemClickListener {
                //get the choice from categories and display it on the text view
                val choice = (it.title).toString()//show dialog
                binding.showChangedCategory.setText(choice)
                // Toast.makeText(this, choice, Toast.LENGTH_SHORT).show()
                true
            }//Button click
            //Display the list of categories
            popup.show()
        }


        //set up submit button
        binding.submitChange.setOnClickListener {

            val title = binding.changeItemName.text.toString()
            val p = binding.changeItemPrice.text.toString()
            val price = p.toInt()
            val category = binding.showChangedCategory.text.toString()

            //first get the item id of the element we woud like to edit
            val id = db.getItemID(item)
            //call the update function
            Toast.makeText(binding.root.context, "$title $price $category", Toast.LENGTH_LONG)
                .show()


            db.updateItem(title, price.toDouble(), 1, category, id)
            transfert(category)


        }

        binding.cancelChange.setOnClickListener {
            val redirect: String = db.getCategoryName(db.getItemID(item))
            transfert(redirect)
        }


    }

    private fun transfert(act: String) {

        if (act == "Food") {
            startActivity(Intent(this, FoodItems::class.java))
        }

        if (act == "Advertisement") {
            startActivity(Intent(this, AdvertisementItem::class.java))
        }

        if (act == "Beauty") {
            startActivity(Intent(this, BeautyItem::class.java))
        }

        if (act == "Education") {
            startActivity(Intent(this, EducationItem::class.java))
        }

        if (act == "Electronics") {
            startActivity(Intent(this, ElectronicsItem::class.java))
        }

        if (act == "Health") {
            startActivity(Intent(this, HealthItem::class.java))
        }

        if (act == "Laundry") {
            startActivity(Intent(this, LaundryItem::class.java))
        }

        if (act == "Other") {
            startActivity(Intent(this, OtherItem::class.java))
        }

    }
}