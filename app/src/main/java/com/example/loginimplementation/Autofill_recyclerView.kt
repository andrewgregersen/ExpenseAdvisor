package com.example.loginimplementation

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginimplementation.model.Autofill_Adapter
import com.example.loginimplementation.model.Autofill_Data
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_autofill_recycler_view.*
import kotlinx.android.synthetic.main.autofill_item.*


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

        var database= FirebaseDatabase.getInstance().getReference("Items")
        autoData = ArrayList<Autofill_Data>()


        for(i in 0 until myList.size step 2){

            try {
                var it = myList.get(i).toString()
                autoData.add(Autofill_Data(R.drawable.food, it, myList.get(i+1), "1", category_spinner.setSelection(0)))

                //if value has space
                var go = true

                it.split("\\s".toRegex()).forEach { item ->
                    //use item
                    if(go){
                        var modify = item.trim()
                        val re = Regex("[^A-Za-z0-9]")
                        var modify2 = re.replace(modify,"")

                        database.child(modify2).get().addOnSuccessListener {

                            if(it.exists()){
                                val name = it.child("name").value
                                val category = it.child("category").value
                                Toast.makeText(this, "categorie for " + name.toString() + " is " + category.toString(), Toast.LENGTH_LONG).show()
                                var size= autoData.size - 1



                            }
                        }

                    }else{
                        return@forEach
                    }
                }

            }catch (e: Exception){
                continue
            }

        }













//        val category = arrayOf(
//            "Food.txt","Electronics.txt","Education.txt","Health.txt","Laundry.txt",
//            "Advertisement.txt","Beauty.txt"
//        )
//
//
//        for(i in 0 until myList.size step 2){  //loop through the list and increment by two
//
//            try {
//                val item = myList.get(i).toString()
//
//                autoData.add(Autofill_Data(R.drawable.food, item, myList.get(i+1), "1"))
//                //Toast.makeText( this.applicationContext, item.toString()+ " looking for categorie....", Toast.LENGTH_LONG).show()
//
//                var isThere = false
//                for (i in 0..category.size){
//                    var file= category.get(i)
//
//                    var fileText:String = applicationContext.assets.open(file).bufferedReader().use{
//                        it.readText()
//                    }
//                    if (fileText.contains(item, ignoreCase = true)){
//                        Toast.makeText( this.applicationContext, item.toString()+ "  in Categorie " + file, Toast.LENGTH_LONG).show()
//                        isThere = true
//                    }
//                }
//
//                if(!isThere){
//                    Toast.makeText( this.applicationContext, item.toString()+ "  in Categorie OTHERS", Toast.LENGTH_LONG).show()
//                }
//
//            }catch (e: Exception){
//                continue
//            }
//        }

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