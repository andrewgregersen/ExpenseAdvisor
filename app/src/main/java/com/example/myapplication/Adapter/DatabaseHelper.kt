package com.example.myapplication.Adapter

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHelper(var Context: Context):
    SQLiteOpenHelper(Context, DATABASE_NAME, null, DATABASE_VERSION) {


 companion object{

     private const val DATABASE_NAME= "Receipt_Advisor_DB"
     private const val DATABASE_VERSION= 1

    //Table user
     private const val USER = "USER"
     private const val COLUMN_ID = "ID"

     //Table Receipt
     private const val RECEIPT = "RECEIPT"
     private const val COLUMN_RECEIPT_ID = "RECEIPT_ID"
     private const val COLUMN_RECEIPT_USER_ID = "UID"
     private const val COLUMN_TOTAL = "TOTAL"
     private const val COLUMN_STORE = "STORE"

     //Table Item
     private const val ITEM = "ITEM"
     private const val COLUMN_ITEM_ID = "ITEM_ID"
     private const val COLUMN_ITEM_NAME = "INAME"
     private const val COLUMN_PRICE = "PRICE"

     //Table Category
     private const val CATEGORY = "CATEGORY"
     private const val COLUMN_CATEGORY_ID = "CID"
     private const val COLUMN_CATEGORY_NAME = "CNAME"

     //Table Profile
     private const val PROFILE = "PROFILE"
     private const val COLUMN_PROFILE_TYPE = "TYPE"
     private const val COLUMN_PROFILE_USER_ID = "PROFILE_USER_ID"
     private const val COLUMN_BUDGET = "BUDGET"
     private const val COLUMN_FAVORITE = "FAVORITE"

     //Table Notification
     private const val NOTIFICATION = "NOTIFICATION"
     private const val COLUMN_NOTIFICATION_ID = "NID"
     private const val COLUMN_NOTIFICATION_USER_ID = "NOTIFICATION_UID"
     private const val COLUMN_DESCRIPTION = "DESCRIPTION"

     //Table Purchase
     private const val PURCHASE = "PURCHASE"
     private const val COLUMN_DATE = "DATE"
     private const val COLUMN_PURCHASE_USER_ID = "PURCHASE_UID"
     private const val COLUMN_PURCHASE_ITEM_ID = "PURCHASE_ITEM_ID"

     //Table Contains
     private const val CONTAINS = "CONTAINS"
     private const val COLUMN_CONTAINS_RECEIPT_ID = "CONTAINS_RID"
     private const val COLUMN_CONTAINS_ITEM_ID = "CONTAINS_ITEM_ID"

     //Table Belong
     private const val BELONG = "BELONG"
     private const val COLUMN_BELONG_ITEM_ID = "BELONG_ITEM_ID"
     private const val COLUMN_BELONG_CATEGORY_ID = "BELONG_CID"
 }



    override fun onCreate(db: SQLiteDatabase?) {

        //Here we are creating all the table

        //1. Table user
        val createTableUser= "CREATE TABLE " + USER +" (" +
                COLUMN_ID +" VARCHAR(256) PRIMARY KEY)"
        db?.execSQL(createTableUser)

        //2. Table Item
        val createTableItem= "CREATE TABLE " + ITEM +" (" +
                COLUMN_ITEM_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COLUMN_ITEM_NAME + " VARCHAR(256) NOT NULL," + COLUMN_PRICE +" INTEGER)"
        db?.execSQL(createTableItem)

        //3.Table Category
        val createTableCategory= "CREATE TABLE " + CATEGORY +" (" +
                COLUMN_CATEGORY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COLUMN_CATEGORY_NAME + " VARCHAR(256))"
        db?.execSQL(createTableCategory)

        //4.Table profile
        val createTableProfile = "CREATE TABLE " + PROFILE + " (" + COLUMN_PROFILE_USER_ID + " INTEGER, "+
                COLUMN_PROFILE_TYPE + " VARCHAR(256), " + COLUMN_BUDGET + " INTEGER, " + COLUMN_FAVORITE+ " VARCHAR(256), PRIMARY KEY (" +
                COLUMN_PROFILE_USER_ID +
                ", "+ COLUMN_PROFILE_TYPE + "), FOREIGN KEY("+ COLUMN_PROFILE_USER_ID +") " +
                "REFERENCES "+ USER + "("+ COLUMN_ID +"))"
        db?.execSQL(createTableProfile)


        //5. Table Notification
        val createTableNotification= "CREATE TABLE " + NOTIFICATION + " (" + COLUMN_NOTIFICATION_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COLUMN_NOTIFICATION_USER_ID + " INTEGER, " + COLUMN_DESCRIPTION +
                " VARCHAR(256), FOREIGN KEY("+ COLUMN_NOTIFICATION_USER_ID +") " +
                "REFERENCES "+ USER + "("+ COLUMN_ID +"))"
        db?.execSQL(createTableNotification)

        //6. Table Purchase
        val createTablePurchase= "CREATE TABLE " + PURCHASE + " (" + COLUMN_DATE + " DATE, "+
                COLUMN_PURCHASE_USER_ID + " INTEGER, " + COLUMN_PURCHASE_ITEM_ID + " INTEGER, PRIMARY KEY ("+
                COLUMN_PURCHASE_ITEM_ID + ", "+ COLUMN_PURCHASE_USER_ID + "), FOREIGN KEY("+ COLUMN_PURCHASE_USER_ID +") " +
                "REFERENCES "+ USER + "("+ COLUMN_ID +"))"
        db?.execSQL(createTablePurchase)

        //7. Table Contains
        val createTableContains= "CREATE TABLE " + CONTAINS + " (" + COLUMN_CONTAINS_ITEM_ID + " INTEGER, "+
                COLUMN_CONTAINS_RECEIPT_ID + " INTEGER, PRIMARY KEY ("+ COLUMN_CONTAINS_ITEM_ID +
                ", "+ COLUMN_CONTAINS_RECEIPT_ID + "), FOREIGN KEY("+ COLUMN_CONTAINS_ITEM_ID +") " +
                "REFERENCES "+ ITEM + "("+ COLUMN_ITEM_ID +"), FOREIGN KEY ("+ COLUMN_CONTAINS_RECEIPT_ID +") "+
                "REFERENCES "+ RECEIPT + "("+ COLUMN_RECEIPT_ID + "))"
        db?.execSQL(createTableContains)

        //8. Table Belong
        val createTableBelong= "CREATE TABLE " + BELONG + " (" + COLUMN_BELONG_ITEM_ID + " INTEGER, "+
                COLUMN_BELONG_CATEGORY_ID + " INTEGER, PRIMARY KEY ("+ COLUMN_BELONG_ITEM_ID +
                ", "+ COLUMN_BELONG_CATEGORY_ID + "), FOREIGN KEY("+ COLUMN_BELONG_ITEM_ID +") " +
                "REFERENCES "+ ITEM + "("+ COLUMN_ITEM_ID +"), FOREIGN KEY ("+ COLUMN_BELONG_CATEGORY_ID +") "+
                "REFERENCES "+ CATEGORY + "("+ COLUMN_CATEGORY_ID + ") ON DELETE CASCADE)"
        db?.execSQL(createTableBelong)


//        val createTable= "CREATE TABLE " + TABLE_NAME +" (" +
//                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
//                COL_NAME + " VARCHAR(256)," + COL_AGE +" INTEGER)";
//
//        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    /*****************************************/
    /* FUNCTION OF INSERTION IN THE DATABASE */
    /****************************************/

    //This function create a category
    fun insertcat(category: String) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_CATEGORY_NAME, category)

        if(!isCategory(category)){
            val result = db.insert(CATEGORY, null, cv)
        }
      // else
           //Toast.makeText(Context, "categoty " + category + " exist already", Toast.LENGTH_LONG).show()
    }

    //This function insert a item ( Places it to it's category as well )
    fun insertItem(itemName: String, price:Int, category: String){
        /* STEP 1, INSERT IN ITEM TABLE */
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_ITEM_NAME, itemName)
        cv.put(COLUMN_PRICE, price)

        val result = db.insert(ITEM, null, cv)
        if (result == -1.toLong()) {
            Toast.makeText(Context, "Failed to insert category: STEP 1", Toast.LENGTH_LONG).show()
            return
        }
        else
            Toast.makeText(Context, "Success to insert item, Step 1", Toast.LENGTH_LONG).show()

        /* STEP 2, GET ITEM ID */
        var id= getLastItemID()


        /**** STEP 3 LINK ITEM AND IT'S CATEGORY ****/
        val db3 = this.writableDatabase
        val cv3= ContentValues()
        cv3.put(COLUMN_BELONG_ITEM_ID, id)
        cv3.put(COLUMN_BELONG_CATEGORY_ID, getCategoryID(category))

        if(getCategoryID(category) == -1){
            Toast.makeText(Context, "Failed!! CATEGORY ID doesn't exist: STEP 3", Toast.LENGTH_LONG).show()
            return
        }

        val solution= db3.insert(BELONG, null, cv3)

        if (solution == -1.toLong())
            Toast.makeText(Context, "Failed to insert in STEP 3", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(Context, "Item insertion success", Toast.LENGTH_LONG).show()


        Toast.makeText(Context, "TOUT VAS BIEN", Toast.LENGTH_LONG).show()

        db.close()
    }


    fun insertprofil() {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_PROFILE_TYPE, "one")
        cv.put(COLUMN_PROFILE_USER_ID, 1)
        cv.put(COLUMN_BUDGET, 1000)
        cv.put(COLUMN_FAVORITE, "one")

        val result = db.insert(PROFILE, null, cv)

        if (result == -1.toLong())
            Toast.makeText(Context, "Failed", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(Context, "Success", Toast.LENGTH_LONG).show()
    }

    fun insertnotification() {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_NOTIFICATION_USER_ID, "one")
        cv.put(COLUMN_DESCRIPTION, "one and one")


        val result = db.insert(NOTIFICATION, null, cv)

        if (result == -1.toLong())
            Toast.makeText(Context, "Failed", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(Context, "Success", Toast.LENGTH_LONG).show()
    }

    fun insertcont() {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_CONTAINS_ITEM_ID, 1)
        cv.put(COLUMN_CONTAINS_RECEIPT_ID, 1)


        val result = db.insert(CONTAINS, null, cv)

        if (result == -1.toLong())
            Toast.makeText(Context, "Failed", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(Context, "Success", Toast.LENGTH_LONG).show()
    }

    fun insertbelong() {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_BELONG_CATEGORY_ID, 1)
        cv.put(COLUMN_BELONG_ITEM_ID, 1)


        val result = db.insert(BELONG, null, cv)

        if (result == -1.toLong())
            Toast.makeText(Context, "Failed", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(Context, "Success", Toast.LENGTH_LONG).show()
    }








    /*****************************************/
    /* FUNCTION OF RETRIEVING IN THE DATABASE */
    /****************************************/

    // Get the all the categories in the application
    fun getCategories(): MutableList<String> {

        var list: MutableList<String> = ArrayList()

        val db = this.readableDatabase
        val query= "Select " + COLUMN_CATEGORY_NAME + " from " + CATEGORY
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException){
            db.execSQL(query)
            return ArrayList()
        }

        var name:String

        if(cursor.moveToFirst()){
            do {

                 name= cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_NAME))
                list.add(name)
                //Toast.makeText(Context, name, Toast.LENGTH_LONG).show()

            }while (cursor.moveToNext())
        }

        return list

    }

    fun getCategoryID(category: String): Int {
        val db = this.readableDatabase
        val query = "SELECT " + COLUMN_CATEGORY_ID + " FROM " + CATEGORY + " WHERE " +
                COLUMN_CATEGORY_NAME + " = '"+category+"'"
        var cursor = db.rawQuery(query, null)

        if(!cursor.moveToNext()){
            return -1
            //Toast.makeText(Context, cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_ID)), Toast.LENGTH_LONG).show()
        }
        var id = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_ID)).toInt()
        return id
    }


    fun isCategory(category: String): Boolean{
        val db = this.readableDatabase
        val query = "SELECT " + COLUMN_CATEGORY_ID + " FROM " + CATEGORY + " WHERE " +
                COLUMN_CATEGORY_NAME + " = '"+category+"' " + " ORDER BY " + COLUMN_CATEGORY_ID + " LIMIT 1"
        var cursor = db.rawQuery(query, null)

        if(!cursor.moveToNext()){
           // Toast.makeText(Context, "category "+ category + " doesn't exist", Toast.LENGTH_LONG).show()
            return false
        }
        var id = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_ID)).toInt()
        //Toast.makeText(Context,"id exist and it's " + id, Toast.LENGTH_LONG).show()
        return true

    }


    fun getItemsOfCategory(category: String): MutableList<String> {

        var categoryFound = getCategoryID(category)

        var list: MutableList<String> = ArrayList()

        val db = this.readableDatabase
        val query= "Select " + COLUMN_ITEM_NAME + ", "+ COLUMN_PRICE + " from " + ITEM +
                " as I, "+ BELONG + " as B, "+ CATEGORY + " as C where I."+ COLUMN_ITEM_ID +
                " = B."+ COLUMN_BELONG_ITEM_ID + " and B." + COLUMN_BELONG_CATEGORY_ID +
                " = " + categoryFound



        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException){
            db.execSQL(query)
            return ArrayList()
        }

        var name:String
        var price:String

        if(cursor.moveToFirst()){
            do {

                name= cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_NAME))
                price= cursor.getString(cursor.getColumnIndex(COLUMN_PRICE))
                list.add(name)
                //Toast.makeText(Context, name +" cost "+ price.toInt(), Toast.LENGTH_LONG).show()


            }while (cursor.moveToNext())
        }
        Toast.makeText(Context, "" + list.size+ " item(s) found ", Toast.LENGTH_LONG).show()
        return list

    }

    fun getOnlyPriceOfItemOfCategory(category: String):MutableList<String>{
        var categoryFound = getCategoryID(category)

        var list: MutableList<String> = ArrayList()

        val db = this.readableDatabase
        val query= "Select " + COLUMN_ITEM_NAME + ", "+ COLUMN_PRICE + " from " + ITEM +
                " as I, "+ BELONG + " as B, "+ CATEGORY + " as C where I."+ COLUMN_ITEM_ID +
                " = B."+ COLUMN_BELONG_ITEM_ID + " and B." + COLUMN_BELONG_CATEGORY_ID +
                " = " + categoryFound

        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException){
            db.execSQL(query)
            return ArrayList()
        }
        var price:String

        if(cursor.moveToFirst()){
            do {
                price= "$"+cursor.getString(cursor.getColumnIndex(COLUMN_PRICE))
                list.add(price)
                //Toast.makeText(Context, "The price is "+ price, Toast.LENGTH_LONG).show()
            }while (cursor.moveToNext())
        }
        //Toast.makeText(Context, "The price size "+ list.size, Toast.LENGTH_LONG).show()
        return list
    }




    fun getLastItemID():Int{

        val db = this.readableDatabase
        val query= "SELECT * FROM " + ITEM + " ORDER BY " + COLUMN_ITEM_ID + " DESC LIMIT 1"

        var cursor = db.rawQuery(query, null)

        if(cursor.moveToNext()){
           // Toast.makeText(Context, cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_ID)), Toast.LENGTH_LONG).show()
        }
        var id = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_ID)).toInt()
        return id
    }

    fun getItemID(itemName: String):Int{

        val db = this.readableDatabase
        val query= "SELECT * FROM " + ITEM + " WHERE " + COLUMN_ITEM_NAME + " = '"+itemName+"' ORDER BY " +
                COLUMN_ITEM_ID + " LIMIT 1"

        var cursor = db.rawQuery(query, null)

        if(cursor.moveToNext()){
            // Toast.makeText(Context, cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_ID)), Toast.LENGTH_LONG).show()
        }
        var id = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_ID)).toInt()
        return id

    }


    /*****************************************/
    /* FUNCTION OF UPDATING IN THE DATABASE */
    /****************************************/

     fun deleteItem(itemName: String){

        val database = this.writableDatabase
        val query = "DELETE FROM "+ ITEM + " WHERE " + COLUMN_ITEM_NAME + " = '"+itemName+"' "

        database.execSQL(query)
        database.close()

     }


    /*****************************************/
    /* FUNCTION OF DELETING IN THE DATABASE */
    /****************************************/

    fun updateItem(itemName: String, price:Int, category: String){
    // This function is used to update an item in the database

        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_ITEM_NAME, itemName)
        cv.put(COLUMN_PRICE, price)

       // database.update(TABLE_NAME, cv, ""+KEY_UserName+"= '"+ username+"' AND "+KEY_CID+"='"+Cid+"'  AND "+KEY_Password+"='"+password+"'" , null);
        //        database.close();

    }

 }