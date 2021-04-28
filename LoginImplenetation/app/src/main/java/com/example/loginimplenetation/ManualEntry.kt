package com.example.loginimplenetation

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginimplenetation.adapter.DatabaseHelper
import com.example.loginimplenetation.databinding.ActivityManualEntryFormatBinding
import com.example.loginimplenetation.databinding.ActivityManualEntryRecyclerViewBinding

class ManualEntry : AppCompatActivity() {

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


        //Init recycler view
        val manager = LinearLayoutManager(this)
        val mAdapter = MyAdapter(listOf(Item()))
        val RecyclerView = findViewById<RecyclerView>(R.id.man_entry_rec).apply {
            layoutManager = manager
            adapter = mAdapter
        }


        //Init Set on Click Listeners

        cancel.setOnClickListener {
            finish() //exit the activity
        }


        addMore.setOnClickListener {
            mAdapter.addItem()
        }

        submit.setOnClickListener {
            alertDialog(mAdapter.getDataSet())
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


    /**
     * Warns the User about how the act of submitting while final, creates more work for them in the long run if they do make a mistake
     * Other than that, gets the users permission to submit the data.
     */

    private fun alertDialog(list: MutableList<Item>) {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage("Are you sure you want to submit this receipt?\n Please double check to make sure there are no mistakes!")
        dialog.setTitle("Submit Your Receipt?")
        dialog.setPositiveButton(
            "Yes"
        ) { dialog, _ -> submitItems(list) }
        dialog.setNegativeButton("No") { dialog, _ ->
            Toast.makeText(
                rcbinding.root.context,
                "Canceling submission",
                Toast.LENGTH_SHORT
            ).show()
        }
        dialog.create().show()
    }


    /**
     * Submits the receipt to the database and then returns the user to the main screen!
     */

    private fun submitItems(list: MutableList<Item>) {
        val db = DatabaseHelper(this)

        //create a new receipt
        db.insertReceipt(
            rcbinding.totalPrice.text.toString().toDouble(),
            rcbinding.storeName.text.toString()
        )
        //store the last receipts DBID
        val receiptID = db.getLastReceiptID()

        //start inserting items
        for (x in list) {
            db.insertItem(x.itemName, x.itemPrice, x.itemAmount, x.itemCategory)
            db.insertContains(receiptID, db.getLastItemID())
        }

        Toast.makeText(applicationContext, "Receipt successfully submitted!", Toast.LENGTH_LONG)
            .show()
        finish()
    }


    /**
     * @author Andrew Gregersen
     * Adapter class for the recycler view.
     * Implements methods to update, delete, and add more elements, starting with a single one.
     * @param mData: A list of "Items" that would appear on a receipt
     */

    class MyAdapter(private val mData: List<Item>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

        private var mDataList: MutableList<Item> = mData as MutableList<Item>


        inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
            fun bind(item: Item, index: Int) {

                //init textviews and other things
                val itemName = view.findViewById<EditText>(R.id.idItemName)
                val itemPrice = view.findViewById<EditText>(R.id.idPrice)
                val itemAmount = view.findViewById<EditText>(R.id.itemQuantity)
                val itemCategorySelector = view.findViewById<Button>(R.id.idCategory)
                val removebtn = view.findViewById<ImageButton>(R.id.imageButtonMERF)
                val itemCategory = view.findViewById<TextView>(R.id.idCategory)


                //init removebtn onClickListener
                removebtn.setOnClickListener {
                    deleteItem(index)
                }
                //init itemCategory
                itemCategorySelector.setOnClickListener {
                    var popup = PopupMenu(view.context, itemCategorySelector)
                    popup.inflate(R.menu.menu_categorie_manual)
                    popup.setOnMenuItemClickListener {
                        //get the choice from categories and display it on the text view
                        itemCategory.text = it.title.toString();true
                        // Toast.makeText(this, choice, Toast.LENGTH_SHORT).show()
                    }
                    //Display the list of categories
                    popup.show()
                }

                //init everything else
                itemName.addTextChangedListener(object : TextWatcher {
                    /**
                     * This method is called to notify you that, within `s`,
                     * the `count` characters beginning at `start`
                     * are about to be replaced by new text with length `after`.
                     * It is an error to attempt to make changes to `s` from
                     * this callback.
                     */
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                        //Nothing to implement here, I dont have anything to do to the text in the box
                        //before the user updates it
                    }

                    /**
                     * This method is called to notify you that, within `s`,
                     * the `count` characters beginning at `start`
                     * have just replaced old text that had length `before`.
                     * It is an error to attempt to make changes to `s` from
                     * this callback.
                     */
                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                    }

                    /**
                     * This method is called to notify you that, somewhere within
                     * `s`, the text has been changed.
                     * It is legitimate to make further changes to `s` from
                     * this callback, but be careful not to get yourself into an infinite
                     * loop, because any changes you make will cause this method to be
                     * called again recursively.
                     * (You are not told where the change took place because other
                     * afterTextChanged() methods may already have made other changes
                     * and invalidated the offsets.  But if you need to know here,
                     * you can use [Spannable.setSpan] in [.onTextChanged]
                     * to mark your place and then look up from here where the span
                     * ended up.
                     */
                    override fun afterTextChanged(s: Editable?) {
                        mDataList[adapterPosition].itemName = s.toString()
                    }

                })

                itemAmount.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                    }

                    override fun afterTextChanged(s: Editable?) {
                        mDataList[adapterPosition].itemAmount = s.toString().toInt()
                    }

                })

                itemPrice.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                    }

                    override fun afterTextChanged(s: Editable?) {
                        mDataList[adapterPosition].itemPrice = s.toString().toDouble()
                    }

                })

            }
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val vh = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_manual_entry_format, parent, false)
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
            mData[position]?.let { holder.bind(it, position) }
        }

        /**
         * Returns the total number of items in the data set held by the adapter.
         *
         * @return The total number of items in this adapter.
         */
        override fun getItemCount(): Int {
            return mDataList.size
        }


        /**
         * Returns the data gathered from the users input
         * @return The list of all items for a single receipt
         */

        fun getDataSet(): MutableList<Item> {
            return mDataList
        }

        /**
         *Removes an item from the recycler view
         */

        fun deleteItem(index: Int) {
            if(itemCount!=1) {
                mDataList.removeAt(index)
                notifyDataSetChanged()
            }
        }

        /**
         * Adds an Item to the data list, and informs the Recycler View that a new item has been updated
         */
        fun addItem() {
            mDataList.add(Item())
            notifyItemInserted(itemCount)
        }


    }

    /**
     * Small class with basic constructor to represent a single item in a list of many
     * @param itemName Holds the items name
     * @param itemPrice Holds the items cost
     * @param itemAmount Holds how many of said item there is
     * @param itemCategory Holds the value for the items category
     */
    class Item(
        var itemName: String = "",
        var itemPrice: Double = 0.0,
        var itemAmount: Int = 0,
        var itemCategory: String = ""
    )
}