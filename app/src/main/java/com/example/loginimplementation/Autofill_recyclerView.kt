package com.example.loginimplementation

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginimplementation.model.Autofill_Adapter
import com.example.loginimplementation.model.Autofill_Data
import kotlinx.android.synthetic.main.activity_autofill_recycler_view.*
import kotlinx.android.synthetic.main.autofill_item.*
import java.io.BufferedReader
import java.io.File
import java.io.InputStream


class Autofill_recyclerView : AppCompatActivity() {
    private lateinit var autofillAdapter:Autofill_Adapter
    private lateinit var autoData:ArrayList<Autofill_Data>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autofill_recycler_view)
        /**set init*/

        val intent = intent
        val date = intent.getStringExtra("date")
        val store = intent.getStringExtra("store")
        val total = intent.getStringExtra("total")
        val myList = getIntent().getSerializableExtra("finalList") as ArrayList<String>
        val category = arrayOf(
            "Food.txt","Electronics.txt","Education.txt","Health.txt","Laundry.txt",
            "Advertisement.txt","Beauty.txt"
        )
        autoData = ArrayList<Autofill_Data>()

        for(i in 0 until myList.size step 2){  //loop through the list and increment by two

            try {
                val item = myList.get(i).toString()

                autoData.add(Autofill_Data(R.drawable.food, item, myList.get(i+1), "1"))
                //Toast.makeText( this.applicationContext, item.toString()+ " looking for categorie....", Toast.LENGTH_LONG).show()

                var isThere = false
                for (i in 0..category.size){
                    var file= category.get(i)
                    //Toast.makeText( this.applicationContext, file, Toast.LENGTH_LONG).show()
//                    val inputStream: InputStream = File(file).inputStream()
//                    val lineList = mutableListOf<String>()
//
//                    inputStream.bufferedReader().forEachLine { lineList.add(it) }
//                    lineList.forEach{
//                        Toast.makeText( this.applicationContext, it + " aichhh ", Toast.LENGTH_LONG).show()
//
//
//                        if (it.contains(item, ignoreCase = true) || item.contains(it,ignoreCase = true)) {
//                            Toast.makeText( this.applicationContext, item.toString()+ "  in Categorie " + file, Toast.LENGTH_LONG).show()
//                            isThere = true
//                            return@forEach
//                        }
//                    }

                    var fileText:String = applicationContext.assets.open(file).bufferedReader().use{
                        it.readText()
                    }
                    if (fileText.contains(item, ignoreCase = true)){
                        Toast.makeText( this.applicationContext, item.toString()+ "  in Categorie " + file, Toast.LENGTH_LONG).show()
                        isThere = true
                    }
                }

                if(!isThere){
                    Toast.makeText( this.applicationContext, item.toString()+ "  in Categorie OTHERS", Toast.LENGTH_LONG).show()
                }

            }catch (e: Exception){
                continue
            }
        }

        autofillAdapter = Autofill_Adapter(autoData)
        autofillRecycler.layoutManager = LinearLayoutManager(this)
        autofillRecycler.adapter = autofillAdapter

        val storeText = findViewById<EditText>(R.id.StoreText)
        val dateText = findViewById<EditText>(R.id.DateText)
        val totalText = findViewById<EditText>(R.id.TotalText)

        storeText.setText(store)
        dateText.setText(date)
        totalText.setText(total)

    }


}