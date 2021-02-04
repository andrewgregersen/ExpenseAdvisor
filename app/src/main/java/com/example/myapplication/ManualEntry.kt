package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Adapter.DatabaseHelper
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_manual_entry.*

class ManualEntry : AppCompatActivity() {

    private var itemName: EditText? = null
    private var itemCategory: TextView? = null
    private var itemPrice: EditText? = null
    private var itemStore: EditText? = null
    private var choice: String = ""
    //private var cancel: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manual_entry)


        //Declare all entry point
        itemName = findViewById(R.id.idItemName) as EditText
        itemCategory= findViewById(R.id.cat_choice) as TextView
        itemPrice = findViewById(R.id.idPrice) as EditText
        itemStore = findViewById(R.id.idStoreName) as EditText

        //Show popup for categories
        idCategorie.setOnClickListener {
            var popup = PopupMenu(this, idCategorie)
            popup.inflate(R.menu.menu_categorie_manual)
            popup.setOnMenuItemClickListener {
                //get the choice from categories and display it on the text view
                choice = (it.title).toString()
                itemCategory!!.text = choice
               // Toast.makeText(this, choice, Toast.LENGTH_SHORT).show()

                true
            }
            //Display the list of categories
            popup.show()
        }


        btCancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent)
            Toast.makeText(applicationContext, "Cancel", Toast.LENGTH_LONG).show()
        }

        btSubmit.setOnClickListener{
            val db = DatabaseHelper(this)

            val name: String = itemName!!.text.toString()
            val category: String = choice
            val price: String = itemPrice!!.text.toString().trim()
            var finalPrice: Int = 0

            try{
                finalPrice = Integer.parseInt(price);
            } catch(e: NumberFormatException){ // handle your exception
                e.message
            }
            var store: String = itemStore!!.text.toString().trim()
            db.insertItem(name, finalPrice, category )
            //Toast.makeText(applicationContext, name + " "+ finalPrice+ " "+ category, Toast.LENGTH_LONG).show()

            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent)
            Toast.makeText(applicationContext, "Submit", Toast.LENGTH_LONG).show()

        }




    }
}