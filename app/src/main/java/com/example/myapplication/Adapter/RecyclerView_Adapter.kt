package com.example.myapplication.Adapter

import android.app.AlertDialog
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.confirm_update_item.view.*
import java.util.zip.Inflater

class RecyclerView_Adapter(private var titles: List<String>, private var details: List<String>, private var images:List<Int>):
RecyclerView.Adapter<RecyclerView_Adapter.ViewHolder>(){






    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){



        val itemTitle: TextView= itemView.findViewById(R.id.history_title)
        val itemDetail: TextView= itemView.findViewById(R.id.history_description)
        val itemPicture: ImageView= itemView.findViewById(R.id.historyImage)
        val deleteItem: ImageView= itemView.findViewById(R.id.deleteItem)
        val editItem: ImageView= itemView.findViewById(R.id.editItem)



        init {
            deleteItem.setOnClickListener { v: View ->
                val position: Int= adapterPosition
                //when click on delete, ask for confirmation
                //showAlertDialog(v)

                MaterialAlertDialogBuilder(itemView.getContext())
                        .setTitle("Warning!")
                        .setMessage("Do you want to delete this item from the category? ")
                        .setNegativeButton("No"){dialog, which ->
                            Toast.makeText(itemView.context, "Cancel ", Toast.LENGTH_SHORT).show()

                        }
                        .setPositiveButton("Yes"){dialog, which ->

                            val db = DatabaseHelper(itemView.getContext())
                            val item:String = itemTitle!!.text.toString()
                            db.deleteItem(item)

                            notifyItemRemoved(position)
                        }
                        .show()

            }

            editItem.setOnClickListener {v:View ->
                val position: Int= adapterPosition

                val db = DatabaseHelper(v.getContext())
                //get the current item Title
                val myItem= itemTitle.text.toString()
                //get Item Id
                val myId: Int = db.getItemID(myItem)
                //get Itemcategory
                val mycategoryIdInBelong= db.getItemCategoryInBelong(myId)
                val categoryName= db.getCategoryName(mycategoryIdInBelong)
                //getItemPrice
                val currentPrice= db.getPriceOfSingleItem(myId)

                Toast.makeText(v.getContext(), ""+ itemTitle.text.toString() + " " + itemDetail.text.toString(), Toast.LENGTH_LONG).show()

                View.inflate(v.context, R.layout.confirm_update_item, null)



                var changeCategory: Button? = v.findViewById(R.id.changeCategory)
                var changeItemName: EditText?=  v.findViewById(R.id.changeItemName)
                var changeItemPrice: EditText?= v.findViewById(R.id.changeItemPrice)
                var showChangedCategory: EditText?=  v.findViewById(R.id.showChangedCategory)
//
//
                //inflate the dialogue
                val mDialogView= LayoutInflater.from(v.getContext()).inflate(R.layout.confirm_update_item,null)
                //AlertDialogBuilder
                val mBuilder= AlertDialog.Builder(v.getContext())
                    .setView(mDialogView)
                    .setTitle(itemTitle.text.toString() + ", " + itemDetail.text.toString()
                            + "\nCategorie = "+ categoryName )




                 //get the previous item title and price

                changeItemName?.setText("maman")
                changeItemPrice?.setText("7")
                showChangedCategory?.setText("Yep")

                Toast.makeText(v.getContext(), ""+ changeCategory?.text.toString() + " " + changeItemName?.text.toString() + " " + showChangedCategory?.text.toString(), Toast.LENGTH_LONG).show()

                //Set up the selection for the choice of category
                changeCategory?.setOnClickListener {
                    var popup = PopupMenu(v.getContext(), changeCategory)
                    popup.inflate(R.menu.menu_categorie_manual)
                    popup.setOnMenuItemClickListener {
                        //get the choice from categories and display it on the text view
                        val choice = (it.title).toString()//show dialog
                        showChangedCategory!!.text = choice as Editable?
                        // Toast.makeText(this, choice, Toast.LENGTH_SHORT).show()
                        true
                    }//Button click
                    //Display the list of categories
                    popup.show()
                }
                //show the dialogue
                val mAlertDialog= mBuilder.show()

                //set up submit button
                mDialogView.submitChange.setOnClickListener {
                    mAlertDialog.dismiss()
                    val title= mDialogView.changeItemName.text.toString()
                    val p= mDialogView.changeItemPrice.text.toString()
                    val price= p.toInt()
                    val category= mDialogView.showChangedCategory.text.toString()

                    //add those value to the update function
                     val db = DatabaseHelper(v.getContext())
                    //first get the item id of the element we woud like to edit
                     val id= db.getItemID(itemTitle!!.text.toString())
                     //call the update function
                    Toast.makeText(v.getContext(), ""+ title + " " + price.toString()+ " " + category, Toast.LENGTH_LONG).show()


                    db.updateItem(title, price, category, myId )
                    notifyItemChanged(position)

                }

                mDialogView.cancelChange.setOnClickListener {
                     mAlertDialog.dismiss()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        //cte iemlayout before
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDetail.text= details[position]
        holder.itemPicture.setImageResource(images[position])
    }


    override fun getItemCount(): Int {
        return titles.size
    }



    //Create a function that show an alerte when deleting an item
    fun showAlertDialog(itemView: View){

        MaterialAlertDialogBuilder(itemView.getContext())
            .setTitle("Warning!")
            .setMessage("Do you want to delete this item from the category? ")
            .setNegativeButton("No"){dialog, which ->
                Toast.makeText(itemView.context, "Cancel ", Toast.LENGTH_SHORT).show()

            }
            .setPositiveButton("Yes"){dialog, which ->
                val itemTitle: TextView= itemView.findViewById(R.id.history_title)
                val db = DatabaseHelper(itemView.getContext())
                val item:String = itemTitle!!.text.toString()
                db.deleteItem(item)
            }
            .show()

    }






        



}