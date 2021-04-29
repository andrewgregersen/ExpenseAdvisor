package com.example.loginimplementation

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginimplementation.model.Autofill_Adapter
import com.example.loginimplementation.model.Autofill_Data
import kotlinx.android.synthetic.main.activity_autofill_recycler_view.*


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

        autoData = ArrayList<Autofill_Data>()

        for(i in 0 until myList.size step 2){  //loop through the list and increment by two

            try {
                autoData.add(Autofill_Data(R.drawable.food, myList.get(i), myList.get(i+1), "1"))
            }catch (e: Exception){
                autoData.add(Autofill_Data(R.drawable.food, "Empty", "Empty", "1"))
            }
        }

        autofillAdapter = Autofill_Adapter(autoData)
        autofillRecycler.layoutManager = LinearLayoutManager(this)
        autofillRecycler.adapter = autofillAdapter

        val storeText = findViewById<EditText>(R.id.StoreText)
        val dateText = findViewById<EditText>(R.id.DateText)

        storeText.setText(store)
        dateText.setText(date)





    }


}