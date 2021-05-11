package com.example.loginimplenetation

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
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
import kotlinx.android.synthetic.main.activity_manual_entry_recycler_view.*

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
        val RecyclerView = binding.manEntryRec.apply {
            layoutManager = manager
            adapter = mAdapter
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

        binding.addMore.setOnClickListener {
            println("Adding More")
            mAdapter.addItem(Item(binding.itemname.text.toString(),binding.itemCost.text.toString().toDouble(),binding.itemAmount.text.toString().toInt(),binding.catChoice.text.toString()))
            binding.itemAmount.text.clear()
            binding.itemCost.text.clear()
            binding.itemname.text.clear()
            binding.catChoice.text = "NONE"
        }

        binding.clear.setOnClickListener {
            binding.itemAmount.text.clear()
            binding.itemCost.text.clear()
            binding.itemname.text.clear()
            binding.catChoice.text = "NONE"
        }

        binding.SubmitMan.setOnClickListener {
            println("Submitting")


            when {
                TextUtils.isEmpty(binding.totalPrice.text) -> {
                    binding.totalPrice.error =
                        "Please enter a total cost for the items!";binding.totalPrice.requestFocus()
                }
                TextUtils.isEmpty(binding.storeName.text) -> {
                    binding.storeName.error =
                        "Please enter a name for the store you made this purchase at!";binding.storeName.requestFocus()
                }
                TextUtils.isEmpty(binding.taxPaid.text) -> {
                    binding.taxPaid.error =
                        "Please enter the amount of tax you paid on this purchase!";binding.taxPaid.requestFocus()
                }
                !TextUtils.isEmpty(binding.itemname.text) ->{
                    binding.itemname.error =
                        "Please either finish or clear the item"
                }
                !TextUtils.isEmpty(binding.itemCost.text) ->{
                    binding.itemCost.error =
                        "Please either finish or clear the item"
                }
                !TextUtils.isEmpty(binding.itemAmount.text) ->{
                    binding.itemAmount.error =
                        "Please either finish or clear the item"
                    binding.itemAmount.requestFocus()
                }
                !TextUtils.equals(binding.catChoice.text,"NONE") ->{
                    binding.CategoryBtn.error =
                        "Please choose a category!"
                    binding.CategoryBtn.requestFocus()
                }
                else -> alertDialog(mAdapter.mData as ArrayList<Item>) //items in recycler view are not empty

            }



        }


    }











    /**
     * Warns the User about how the act of submitting while final, creates more work for them in the long run if they do make a mistake
     * Other than that, gets the users permission to submit the data.
     */

    private fun alertDialog(list: ArrayList<Item>) {
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

    private fun submitItems(list: ArrayList<Item>) {
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
                    db.insertItem(x.itemName,x.itemPrice,x.itemAmount,x.itemCategory)
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
        private lateinit var parent: ViewGroup
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
            this.parent = parent
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
            //init remove button
            binding.imageButtonMERF.setOnClickListener {
                    deleteItem(position)
                }

            //init edit button
            binding.edit.setOnClickListener {
                deleteItem(position)
                val parBinding = ActivityManualEntryRecyclerViewBinding.inflate(LayoutInflater.from(parent.context))
                parBinding.itemCost.text = currentItem.itemPrice as Editable
                parBinding.itemname.text = currentItem.itemName as Editable
                parBinding.itemAmount.text = currentItem.itemAmount as Editable
                parBinding.catChoice.text = currentItem.itemCategory
            }


            binding.ItemInfo.text = "$position: ${currentItem.itemName}, ${currentItem.itemAmount} for ${currentItem.itemPrice}, ${currentItem.itemCategory} "


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

        private fun deleteItem(index: Int) {
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