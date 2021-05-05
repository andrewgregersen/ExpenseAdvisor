package com.example.loginimplenetation

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginimplenetation.adapter.DatabaseHelper
import com.example.loginimplenetation.databinding.ActivityManualEntryFormatBinding
import com.example.loginimplenetation.databinding.ActivityManualEntryRecyclerViewBinding
import com.example.loginimplenetation.databinding.ItemLayoutBinding

class ManualEntry : AppCompatActivity() {

    private lateinit var binding: ActivityManualEntryRecyclerViewBinding
    //private var cancel: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_manual_entry_recycler_view)


        binding.Return.setOnClickListener {
            finish()
        }
//        binding.Return.setOnClickListener {
//            finish() //exit the activity
//        }



        //Init recycler view
        val manager = LinearLayoutManager(this)
        val mAdapter = MyAdapter(mutableListOf<Item>(Item()))
        val RecyclerView = findViewById<RecyclerView>(R.id.man_entry_rec).apply {
            layoutManager = manager
            adapter = mAdapter
        }

        binding.addMore.setOnClickListener {
            println("Adding More")
            mAdapter.addItem(Item())
        }

        binding.SubmitMan.setOnClickListener {
            println("Submitting")
            when{
//                binding.taxPaid.text.isEmpty() -> {binding.taxPaid.error = "You didn't include a tax amount!"; binding.taxPaid.requestFocus()}
//                binding.storeName.text.isEmpty() -> {binding.storeName.error = "You didn't include the name of the store!"; binding.storeName.requestFocus()}
//                binding.totalPrice.text.isEmpty() -> {binding.totalPrice.error = "You didn't include the total price!"; binding.totalPrice.requestFocus()}
                else ->{
                    val bindings = mAdapter.getBindings()
                    var err = false
                    when{
                        TextUtils.isEmpty(binding.totalPrice.text)-> {binding.totalPrice.error = "Please enter a total cost for the items!";binding.totalPrice.requestFocus()}
                        TextUtils.isEmpty(binding.storeName.text)-> {binding.storeName.error = "Please enter a name for the store you made this purchase at!";binding.storeName.requestFocus()}
                        TextUtils.isEmpty(binding.taxPaid.text) -> {binding.taxPaid.error = "Please enter the amount of tax you paid on this purchase!";binding.taxPaid.requestFocus()}
                        }
                    for(x in bindings){
                        when{
                            x.itemQuantity.text.isEmpty() -> {x.itemQuantity.error = "Please enter the amount of said item!";x.itemQuantity.requestFocus();err=true}
                            x.idPrice.text.isEmpty() -> {x.idPrice.error = "Please enter a price!";x.idPrice.requestFocus();err=true}
                            x.idItemName.text.isEmpty() -> {x.idItemName.error = "Please enter a name!";x.idItemName.requestFocus();err=true}
                            x.catChoice.text.isEmpty() -> {x.CategoryBtn.error = "Please select a category!";x.CategoryBtn.requestFocus();err=true}
                        }
                        if(err)
                            break
                    }
                    if(!err)
                        alertDialog(mAdapter.getBindings()) //items in recycler view are not empty
                }
            }

        }








    }


    /**
     * Warns the User about how the act of submitting while final, creates more work for them in the long run if they do make a mistake
     * Other than that, gets the users permission to submit the data.
     */

    private fun alertDialog(list: ArrayList<ActivityManualEntryFormatBinding>) {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage("Are you sure you want to submit this receipt?\n Please double check to make sure there are no mistakes!")
        dialog.setTitle("Submit Your Receipt?")
        dialog.setPositiveButton(
            "Yes"
        ) { dialog, _ -> submitItems(list) }
        dialog.setNegativeButton("No") { dialog, _ ->
            Toast.makeText(
                binding.root.context,
                "Canceling submission",
                Toast.LENGTH_SHORT
            ).show()
        }
        dialog.create().show()
    }


    /**
     * Submits the receipt to the database and then returns the user to the main screen!
     */

    private fun submitItems(list: ArrayList<ActivityManualEntryFormatBinding>) {
        val db = DatabaseHelper(this)
        println("SubmittingItems")
            if(list.isNotEmpty()) {
                //create a new receipt
                db.insertReceipt(
                    binding.totalPrice.text.toString().toDouble(),
                    binding.storeName.text.toString()
                )
                //store the last receipts DBID
                val receiptID = db.getLastReceiptID()

                //start inserting items

                for (x in list) {
                    db.insertItem(x.idItemName.text.toString(), x.idPrice.text.toString().toDouble(), x.itemQuantity.text.toString().toInt(), x.catChoice.text.toString())
                    db.insertContains(receiptID, db.getLastItemID())
                }

                Toast.makeText(
                    applicationContext,
                    "Receipt successfully submitted!",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }else{
                Toast.makeText(
                    applicationContext,
                    "Failed to Submit!",
                    Toast.LENGTH_SHORT
                ).show()
            }



    }


    /**
     * @author Andrew Gregersen
     * Adapter class for the recycler view.
     * Implements methods to update, delete, and add more elements, starting with a single one.
     * @param mData: A list of "Items" that would appear on a receipt
     */

    class MyAdapter(val mData: MutableList<Item>) : RecyclerView.Adapter<CustomViewHolder>() {
        var lastPos = 0
        private var bindingList: ArrayList<ActivityManualEntryFormatBinding> = ArrayList()
        companion object: DiffUtil.ItemCallback<Item>(){
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return (oldItem.itemAmount == newItem.itemAmount) && (oldItem.itemName == newItem.itemName) && (oldItem.itemPrice == newItem.itemPrice)
            }
        }





        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val vh = LayoutInflater.from(parent.context)
            val binding = ActivityManualEntryFormatBinding.inflate(vh,parent,false)
            return CustomViewHolder(binding)
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
        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            //mData[position].let { holder.bind(it, position)}
            val currentItem = mData[position]
            val binding = holder.binding as ActivityManualEntryFormatBinding
            bindingList.add(position,binding)//add the binding to the current position, doesn't allow for duplicates
            //init remove button
            binding.imageButtonMERF.setOnClickListener {
                    deleteItem(position)
                }

            //init cat_selector
            binding.CategoryBtn.setOnClickListener {
                    val popup = PopupMenu(binding.root.context, binding.CategoryBtn)
                    popup.inflate(R.menu.menu_categorie_manual)
                    popup.setOnMenuItemClickListener {
                        //get the choice from categories and display it on the text view
                        binding.catChoice.text = it.title.toString();true
                        // Toast.makeText(this, choice, Toast.LENGTH_SHORT).show()
                    }
                    //Display the list of categories
                    popup.show()
                }

//            binding.idItemName.addTextChangedListener(object : TextWatcher {
//                    /**
//                     * This method is called to notify you that, within `s`,
//                     * the `count` characters beginning at `start`
//                     * are about to be replaced by new text with length `after`.
//                     * It is an error to attempt to make changes to `s` from
//                     * this callback.
//                     */
//                    override fun beforeTextChanged(
//                        s: CharSequence?,
//                        start: Int,
//                        count: Int,
//                        after: Int
//                    ) {
//                        //Nothing to implement here, I dont have anything to do to the text in the box
//                        //before the user updates it
//                    }
//
//                    /**
//                     * This method is called to notify you that, within `s`,
//                     * the `count` characters beginning at `start`
//                     * have just replaced old text that had length `before`.
//                     * It is an error to attempt to make changes to `s` from
//                     * this callback.
//                     */
//                    override fun onTextChanged(
//                        s: CharSequence?,
//                        start: Int,
//                        before: Int,
//                        count: Int
//                    ) {
//                    }
//
//                    /**
//                     * This method is called to notify you that, somewhere within
//                     * `s`, the text has been changed.
//                     * It is legitimate to make further changes to `s` from
//                     * this callback, but be careful not to get yourself into an infinite
//                     * loop, because any changes you make will cause this method to be
//                     * called again recursively.
//                     * (You are not told where the change took place because other
//                     * afterTextChanged() methods may already have made other changes
//                     * and invalidated the offsets.  But if you need to know here,
//                     * you can use [Spannable.setSpan] in [.onTextChanged]
//                     * to mark your place and then look up from here where the span
//                     * ended up.
//                     */
//                    override fun afterTextChanged(s: Editable?) {
//                        mData[position].itemName = s.toString()
//                    }
//
//                })
//
//                binding.itemQuantity.addTextChangedListener(object : TextWatcher {
//                    override fun beforeTextChanged(
//                        s: CharSequence?,
//                        start: Int,
//                        count: Int,
//                        after: Int
//                    ) {
//                    }
//
//                    override fun onTextChanged(
//                        s: CharSequence?,
//                        start: Int,
//                        before: Int,
//                        count: Int
//                    ) {
//                    }
//
//                    override fun afterTextChanged(s: Editable?) {
//                        mData[position].itemAmount = s.toString().toInt()
//                    }
//
//                })
//
//                binding.idPrice.addTextChangedListener(object : TextWatcher {
//                    override fun beforeTextChanged(
//                        s: CharSequence?,
//                        start: Int,
//                        count: Int,
//                        after: Int
//                    ) {
//                    }
//
//                    override fun onTextChanged(
//                        s: CharSequence?,
//                        start: Int,
//                        before: Int,
//                        count: Int
//                    ) {
//                    }
//
//                    override fun afterTextChanged(s: Editable?) {
//                        mData[position].itemPrice = s.toString().toDouble()
//                    }
//
//                })



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
         * Returns the data gathered from the users input
         * @return The list of all items for a single receipt
         */


        /**
         *Removes an item from the recycler view
         */

        fun deleteItem(index: Int) {
            if(itemCount!=1) {
                mData.removeAt(index)
                notifyDataSetChanged()
                lastPos--
            }
        }

        /**
         * Adds an Item to the data list, and informs the Recycler View that a new item has been updated
         */
        fun addItem(item: Item) {
            Log.d("addItem","$mData.size")
            mData.add(item)
            notifyItemInserted(mData.size)
        }

        fun getBindings():ArrayList<ActivityManualEntryFormatBinding>{
            return bindingList
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

    open class CustomViewHolder(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root)
}