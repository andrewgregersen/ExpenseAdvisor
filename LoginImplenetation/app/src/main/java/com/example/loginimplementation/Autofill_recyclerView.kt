package com.example.loginimplementation

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginimplementation.model.Autofill_Adapter
import com.example.loginimplementation.model.Autofill_Data
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_autofill_recycler_view.*
import kotlinx.android.synthetic.main.autofill_item.*
import java.io.Serializable


class Autofill_recyclerView : AppCompatActivity() {
    private lateinit var autofillAdapter: Autofill_Adapter
    private lateinit var autoData: ArrayList<Autofill_Data>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autofill_recycler_view)
        /**set init*/

        val intent = intent
        val date = intent.getStringExtra("date")
        val store = intent.getStringExtra("store")
        val total = intent.getStringExtra("total")
        val myList = getIntent().getSerializableExtra("finalList") as ArrayList<String>

        var database = FirebaseDatabase.getInstance().getReference("Items")
        autoData = ArrayList<Autofill_Data>()
        var itemList = ArrayList<ItemPass>()


        for (i in 0 until myList.size step 2) {

            var go = true
            var itemTemp = myList.get(i).toString()
            try {
                var it = myList.get(i).toString()
                //if value has space


                it.split("\\s".toRegex()).forEach { item ->
                    //use item
                    if (go) {
                        var modify = item.trim()
                        val re = Regex("[^A-Za-z0-9]")
                        var modify2 = re.replace(modify, "").toLowerCase()

                        database.child(modify2).get().addOnSuccessListener {

                            if (it.exists()) {
                                val name = it.child("name").value
                                val category = it.child("category").value
                                Toast.makeText(this, "categorie for " + name.toString() + " is " + category.toString(), Toast.LENGTH_LONG).show()
                                autoData.add(Autofill_Data(R.drawable.auto, item, myList.get(i + 1), "1", category.toString()))
                                itemList.add(ItemPass(item, myList.get(i + 1),"1", category.toString()))
                                go = false

                            }
                        }
                    }
                }

                if(!go){
                    autoData.add(Autofill_Data(R.drawable.auto, itemTemp, myList.get(i + 1), "1", "Others"))
                    itemList.add(ItemPass(itemTemp, myList.get(i + 1),"1", "Others"))
                }

            } catch (e: Exception) {
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


        submitText.setOnClickListener {

            val temp = autofillAdapter.notifyDataSetChanged()
            var finalList = autofillAdapter.autofillList as ArrayList<Autofill_Data>

            Toast.makeText(this.applicationContext, finalList.toString(), Toast.LENGTH_LONG).show()

            var context= applicationContext
            val intent = Intent(context,ConfirmeMachineLearning::class.java)
            intent.putExtra("ReceiptID",itemList)
            intent.putExtra("Date", date)
            intent.putExtra("Store", store)
            intent.putExtra("Total", total)

            context.startActivity(intent)




        }

    }

    class ItemPass(
            var itemName: String = "",
            var itemPrice: String = " ",
            var itemAmount: String = " ",
            var itemCategory: String = ""


    ): Serializable

    class ItemCreate(
            var itemName: String = "",
            var itemPrice: Double = 0.0,
            var itemAmount: Int = 0,
            var itemCategory: String = ""
    ): Serializable

}