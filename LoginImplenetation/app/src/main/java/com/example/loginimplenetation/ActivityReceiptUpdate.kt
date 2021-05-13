package com.example.loginimplenetation

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginimplenetation.adapter.DatabaseHelper
import com.example.loginimplenetation.adapter.DatabaseHelper.Item
import com.example.loginimplenetation.databinding.ActivityManualEntryFormatBinding
import com.example.loginimplenetation.databinding.ActivityManualEntryRecyclerViewBinding
import java.lang.IndexOutOfBoundsException


class ActivityReceiptUpdate: AppCompatActivity() {

    private lateinit var Binding: ActivityManualEntryRecyclerViewBinding
    private var receiptID: Int = 0
    //private var cancel: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = DataBindingUtil.setContentView(this,R.layout.activity_manual_entry_recycler_view)


        receiptID = this.intent.extras.toString().toInt()

        val list = DatabaseHelper(this).getItemsWithID(receiptID)



        Binding.Return.setOnClickListener {
            finish()
        }
//        binding.Return.setOnClickListener {
//            finish() //exit the activity
//        }



        //Init recycler view
        val manager = LinearLayoutManager(this)
        val mAdapter = MyAdapter(list)
        val RecyclerView = Binding.manEntryRec.apply {
            layoutManager = manager
            adapter = mAdapter
        }

        //init cat_selector
        Binding.CategoryBtn.setOnClickListener {
            val popup = PopupMenu(Binding.root.context, Binding.CategoryBtn)
            popup.inflate(R.menu.menu_categorie_manual)
            popup.setOnMenuItemClickListener {
                //get the choice from categories and display it on the text view
                Binding.catChoice.text = it.title.toString();true
                // Toast.makeText(this, choice, Toast.LENGTH_SHORT).show()
            }
            //Display the list of categories
            popup.show()
        }

        Binding.addMore.setOnClickListener {
            println("Adding More")
            when{
                TextUtils.isEmpty(Binding.itemname.text) ->{
                    Binding.itemname.error =
                        "Please enter the Items name"
                    Binding.itemname.requestFocus()
                }
                TextUtils.isEmpty(Binding.itemCost.text) ->{
                    Binding.itemCost.error =
                        "Please enter the Item Cost"
                    Binding.itemCost.requestFocus()
                }
                TextUtils.isEmpty(Binding.itemAmount.text) ->{
                    Binding.itemAmount.error =
                        "Please enter an amount"
                    Binding.itemAmount.requestFocus()
                }
                TextUtils.equals(Binding.catChoice.text,"NONE") ->{
                    Binding.CategoryBtn.error =
                        "Please choose a category!"
                    Binding.CategoryBtn.requestFocus()
                }
                else -> {
                    mAdapter.addItem(Item(-1,Binding.itemname.text.toString(),Binding.itemCost.text.toString().toDouble(),
                        Binding.itemAmount.text.toString().toInt(),Binding.catChoice.text.toString()))
                    Binding.itemAmount.text.clear()
                    Binding.itemCost.text.clear()
                    Binding.itemname.text.clear()
                    Binding.catChoice.text = getString(R.string.emptyCat)
                }
            }


        }

        Binding.clear.setOnClickListener {
            Binding.itemAmount.text.clear()
            Binding.itemCost.text.clear()
            Binding.itemname.text.clear()
            Binding.catChoice.text = getString(R.string.emptyCat)
        }

        Binding.SubmitMan.setOnClickListener {
            println("Submitting")

            try{
                mAdapter.deleteFirst()
            }catch(e: IndexOutOfBoundsException){
                Binding.SubmitMan.error = "You can't submit an empty list!"
                Binding.SubmitMan.requestFocus()
            }




            when {
                TextUtils.isEmpty(Binding.totalPrice.text) -> {
                    Binding.totalPrice.error =
                        "Please enter a total cost for the items!";Binding.totalPrice.requestFocus()
                }
                TextUtils.isEmpty(Binding.storeName.text) -> {
                    Binding.storeName.error =
                        "Please enter a name for the store you made this purchase at!";Binding.storeName.requestFocus()
                }
                TextUtils.isEmpty(Binding.taxPaid.text) -> {
                    Binding.taxPaid.error =
                        "Please enter the amount of tax you paid on this purchase!";Binding.taxPaid.requestFocus()
                }
                !TextUtils.isEmpty(Binding.itemname.text) ->{
                    Binding.itemname.error =
                        "Please either finish or clear the item"
                }
                !TextUtils.isEmpty(Binding.itemCost.text) ->{
                    Binding.itemCost.error =
                        "Please either finish or clear the item"
                }
                !TextUtils.isEmpty(Binding.itemAmount.text) ->{
                    Binding.itemAmount.error =
                        "Please either finish or clear the item"
                    Binding.itemAmount.requestFocus()
                }
                !TextUtils.equals(Binding.catChoice.text,"NONE") ->{
                    Binding.CategoryBtn.error =
                        "Please choose a category!"
                    Binding.CategoryBtn.requestFocus()
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
                Binding.root.context,
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
                Binding.totalPrice.text.toString().toDouble(),
                Binding.storeName.text.toString()
            )
            //store the last receipts DBID

            //start inserting items

            for (x in list) {
                if(x.itemID==-1){ //incase the user inserts a new item to the receipt
                    db.insertItem(x.itemName,x.itemPrice,x.itemAmount,x.itemCategory)
                    db.insertContains(receiptID,db.getLastItemID())
                }
                else db.updateItem(x.itemName,x.itemPrice,x.itemAmount,x.itemCategory,x.itemID) //otherwise just call the update function

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
        private var lastPos = 0
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
            val parBinding = ActivityManualEntryRecyclerViewBinding.inflate(LayoutInflater.from(parent.context))
            //init remove button
            binding.imageButtonMERF.setOnClickListener {
                deleteItem(position)
            }

            //init edit button
            binding.edit.setOnClickListener {
                Log.i("RecyclerView","Edit Button clicked")
                parBinding.itemCost.setText(currentItem.itemPrice.toString())
                parBinding.itemname.setText(currentItem.itemName)
                parBinding.itemAmount.setText(currentItem.itemAmount)
                parBinding.catChoice.text = currentItem.itemCategory
                deleteItem(position)
                notifyDataSetChanged()
            }


            "$position: ${currentItem.itemName}, ${currentItem.itemAmount} for ${currentItem.itemPrice}, ${currentItem.itemCategory} ".also { binding.ItemInfo.text = it }


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

        fun deleteFirst(){
            if(itemCount>1){
                mData.removeAt(0)
                notifyDataSetChanged()
                lastPos--
            }
            else throw IndexOutOfBoundsException()
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
    open class CustomViewHolder(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root)
}