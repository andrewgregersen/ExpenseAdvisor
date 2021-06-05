package com.example.loginimplenetation

import android.os.Bundle
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.loginimplenetation.databinding.ActivityUpdateBinding

class Update : AppCompatActivity() {


    private lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update)


        // Get the previous value in each tuple:
        //textView.setText("string").toString()


        binding.idCategoryUP.setOnClickListener {
            var popup = PopupMenu(this, binding.idCategoryUP)
            popup.inflate(R.menu.menu_categorie_manual)
            popup.setOnMenuItemClickListener {
                //get the choice from categories and display it on the text view
                binding.catChoice.text = (it.title).toString()
                // Toast.makeText(this, choice, Toast.LENGTH_SHORT).show()

                true
            }
            //Display the list of categories
            popup.show()
        }


    }
}