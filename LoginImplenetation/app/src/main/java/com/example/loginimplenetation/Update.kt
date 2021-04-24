package com.example.loginimplenetation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import com.example.loginimplentation.R

class Update : AppCompatActivity() {

    private var itemName: EditText? = null
    private var itemCategory: TextView? = null
    private var itemPrice: EditText? = null
    private var itemStore: EditText? = null
    private var choice: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        itemName = findViewById(R.id.idItemName)
        itemCategory= findViewById(R.id.cat_choice) as TextView
        itemPrice = findViewById(R.id.idPrice) as EditText
        itemStore = findViewById(R.id.idStoreName) as EditText


        // Get the previous value in each tuple:
        //textView.setText("string").toString()








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




    }
}