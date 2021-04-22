package com.example.loginimplenetation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.loginimplentation.databinding.ActivityUpdateBinding

class Update : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private var choice: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_update)



        // Get the previous value in each tuple:
        //textView.setText("string").toString()








        binding.idCategoryUP.setOnClickListener {
            var popup = PopupMenu(this, binding.idCategoryUP)
            popup.inflate(R.menu.menu_categorie_manual)
            popup.setOnMenuItemClickListener {
                //get the choice from categories and display it on the text view
                choice = (it.title).toString()
                binding.tvCategoryUP!!.text = choice
                // Toast.makeText(this, choice, Toast.LENGTH_SHORT).show()

                true
            }
            //Display the list of categories
            popup.show()
        }




    }
}