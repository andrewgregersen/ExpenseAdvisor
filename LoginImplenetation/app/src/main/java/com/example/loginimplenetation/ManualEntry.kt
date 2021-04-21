package com.example.loginimplementation

import android.content.Intent
import android.os.Bundle
import android.util.Property
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginimplenetation.TextRecognitionActivity
import com.example.loginimplentation.R
import com.example.loginimplentation.databinding.ActivityManualEntryFormatBinding
import com.example.loginimplentation.databinding.ActivityManualEntryRecyclerViewBinding

class ManualEntry : AppCompatActivity() {

    private var itemName: EditText? = null
    private var itemCategory: TextView? = null
    private var itemPrice: EditText? = null
    private var itemStore: EditText? = null
    private var choice: String = ""
    private var itemQuantity: EditText? = null
    private lateinit var rcbinding: ActivityManualEntryRecyclerViewBinding
    private lateinit var fmbinding: ActivityManualEntryFormatBinding
    //private var cancel: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manual_entry_recycler_view)
        //Init bindings
        rcbinding = ActivityManualEntryRecyclerViewBinding.inflate(layoutInflater)
        fmbinding = ActivityManualEntryFormatBinding.inflate(layoutInflater)
        //Init buttons
        val cancel = rcbinding.Return
        val submit = rcbinding.SubmitMan
        val addMore = rcbinding.addMore


        //Init Set on Click Listeners

        cancel.setOnClickListener {
            finish() //exit the activity
        }

        submit.setOnClickListener {
            TODO("NOT YET IMPLEMENTED")
        }

        addMore.setOnClickListener {
            TODO("UPDATE RECYCLER VIEW TO ADD NEW CELL")
        }


        //Init recycler view
        var manager = LinearLayoutManager(this)
        var mAdapter = mAdapter(arrayOfNulls<String>(0))
        val RecyclerView = findViewById<RecyclerView>(R.id.man_entry_rec).apply{
            layoutManager = manager
                adapter = mAdapter
        }




        //Declare all entry point
        /*itemName = findViewById(R.id.idItemName)
        itemCategory= findViewById(R.id.cat_choice)
        itemPrice = findViewById(R.id.idPrice)
        itemStore = findViewById(R.id.idStoreName)
        itemQuantity = findViewById(R.id.text_description)

        //Show popup for categories
        idCategory.setOnClickListener {
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
            val store: String = itemStore!!.text.toString().trim()
            val quantity: String = itemQuantity!!.text.toString().trim()
            var finalPrice: Double = 0.0

            try{
                finalPrice = (Integer.parseInt(price)).toDouble();
            } catch(e: NumberFormatException){ // handle your exception
                e.message
            }

            db.insertItem(name, finalPrice, quantity.toInt(), category)
            //Toast.makeText(applicationContext, name + " "+ finalPrice+ " "+ category, Toast.LENGTH_LONG).show()

            //create the receipt
            db.insertReceipt(finalPrice, store)

            //Link receipt and item
            var itemID= db.getLastItemID()
            var receiptID= db.getLastReceiptID()

            db.insertContains(receiptID, itemID)


            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent)
            Toast.makeText(applicationContext, "Submit", Toast.LENGTH_LONG).show()

        }

         */


    }

    class mAdapter(private val mData: List<>) : RecyclerView.Adapter<mAdapter.ViewHolder>(){

        private lateinit var itemName: EditText
        private lateinit var itemPrice: EditText
        lateinit var itemAmount: EditText
        lateinit var itemCategoryChoice: String
        lateinit var removeItem: ImageButton


            inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view){
                fun bind(text:String){
                    itemName= view.findViewById<EditText>(R.id.idItemName)
                    itemPrice = view.findViewById<EditText>(R.id.idPrice)
                    itemAmount = view.findViewById<EditText>(R.id.itemQuantity)
                    itemCategory = view.findViewById<Button>(R.id.idCategory)

                }
            }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val vh = LayoutInflater.from(parent.context).inflate(R.layout.activity_manual_entry_format,parent,false)
            return ViewHolder(vh)
        }

        /**
         * Called by RecyclerView to display the data at the specified position. This method should
         * update the contents of the [ViewHolder.itemView] to reflect the item at the given
         * position.
         *
         *
         * Note that unlike [android.widget.ListView], RecyclerView will not call this method
         * again if the position of the item changes in the data set unless the item itself is
         * invalidated or the new position cannot be determined. For this reason, you should only
         * use the `position` parameter while acquiring the related data item inside
         * this method and should not keep a copy of it. If you need the position of an item later
         * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
         * have the updated adapter position.
         *
         * Override [.onBindViewHolder] instead if Adapter can
         * handle efficient partial bind.
         *
         * @param holder The ViewHolder which should be updated to represent the contents of the
         * item at the given position in the data set.
         * @param position The position of the item within the adapter's data set.
         */
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            mData[position]?.let { holder.bind(it) }
        }

        /**
         * Returns the total number of items in the data set held by the adapter.
         *
         * @return The total number of items in this adapter.
         */
        override fun getItemCount(): Int {
            return mData.size
        }

        /**
         *Informs the recycler view to remove
         */


    }
}