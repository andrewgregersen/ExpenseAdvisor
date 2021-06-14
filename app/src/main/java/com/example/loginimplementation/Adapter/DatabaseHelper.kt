package com.example.loginimplementation.Adapter

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.io.Serializable

class DatabaseHelper(var Context: Context):
        SQLiteOpenHelper(Context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object{

        private const val DATABASE_NAME= "Receipt_Advisor_DB"
        private const val DATABASE_VERSION= 1
        private var Category_Is_Populated = false

        //Table user
        private const val USER = "USER"
        private const val COLUMN_ID = "ID"

        //Table Receipt
        private const val RECEIPT = "RECEIPT"
        private const val COLUMN_RECEIPT_ID = "RECEIPT_ID"
        //private const val COLUMN_RECEIPT_USER_ID = "UID"
        private const val COLUMN_TOTAL = "TOTAL"
        private const val COLUMN_STORE = "STORE"
        private const val COLUMN_DATE = "DATE"

        //Table Item
        private const val ITEM = "ITEM"
        private const val COLUMN_ITEM_ID = "ITEM_ID"
        private const val COLUMN_ITEM_NAME = "INAME"
        private const val COLUMN_PRICE = "PRICE"
        private const val COLUMN_AMOUNT = "AMOUNT"

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
        //private const val COLUMN_DATE = "DATE"
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
                COLUMN_ID +" INTEGER, PRIMARY KEY(" + COLUMN_ID + "))"
        db?.execSQL(createTableUser)

        //2. Table Item
        val createTableItem= "CREATE TABLE " + ITEM +" (" +
                COLUMN_ITEM_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COLUMN_ITEM_NAME + " VARCHAR(256) NOT NULL," + COLUMN_PRICE +" DOUBLE,"+
                COLUMN_AMOUNT + " INTEGER)"
        db?.execSQL(createTableItem)

        //3.Table Category
        val createTableCategory= "CREATE TABLE " + CATEGORY +" (" +
                COLUMN_CATEGORY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COLUMN_CATEGORY_NAME + " VARCHAR(256))"
        db?.execSQL(createTableCategory)

        //4.Table profile
        val createTableProfile = "CREATE TABLE " + PROFILE + " (" + COLUMN_PROFILE_USER_ID + " INTEGER, "+
                COLUMN_PROFILE_TYPE + " VARCHAR(256), " + COLUMN_BUDGET + " DOUBLE, " + COLUMN_FAVORITE + " VARCHAR(256), PRIMARY KEY (" +
                COLUMN_PROFILE_USER_ID + ", " + COLUMN_FAVORITE + "), FOREIGN KEY("+ COLUMN_PROFILE_USER_ID +") " +
                "REFERENCES "+ USER + "("+ COLUMN_ID +"))"
        db?.execSQL(createTableProfile)


        //5. Table Notification
        val createTableNotification= "CREATE TABLE " + NOTIFICATION + " (" + COLUMN_NOTIFICATION_ID +
                " INTEGER PRIMARY KEY, "+ COLUMN_NOTIFICATION_USER_ID + " INTEGER, " + COLUMN_DESCRIPTION +
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

        //8. Table Receipt

        val createTableReceipt= "CREATE TABLE "+ RECEIPT + " (" + COLUMN_RECEIPT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_TOTAL + " DOUBLE, "+ COLUMN_STORE + " VARCHAR(256), "+ COLUMN_DATE +
                " DATETIME DEFAULT CURRENT_TIMESTAMP) "
        db?.execSQL(createTableReceipt)




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
    fun insertItem(itemName: String, price:Double, amount:Int, category: String){
        /* STEP 1, INSERT IN ITEM TABLE */
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_ITEM_NAME, itemName)
        cv.put(COLUMN_PRICE, price)
        cv.put(COLUMN_AMOUNT,amount)

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


    fun initCat(): Boolean {
        val db = this.writableDatabase
        if (!Category_Is_Populated) {
            val category = arrayOf(
                "Food",
                "Drinks",
                "Electronics",
                "Education",
                "Health",
                "Laundry",
                "Advertisement",
                "Beauty",
                "Sport",
                "Other"
            )
            for (item in category) {
                insertcat(item)
            }
            Category_Is_Populated = true
        }
        return Category_Is_Populated //should always return true
    }


    fun totalSpendOverPeriod(): Double {
        val totals = getCatTotalCost()
        var sum = 0.0
        for (x in totals) {
            sum += x
        }
        return sum
    }

    fun createUser() {

        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_ID, 1.toInt())
        val result = db.insert(USER, null, cv)

        if (result == -1.toLong())
            Toast.makeText(Context, result.toString(), Toast.LENGTH_LONG).show()
        else
            Toast.makeText(Context, "Success user create", Toast.LENGTH_LONG).show()
    }

    fun isUser(): Boolean {

        val db = this.readableDatabase
        val query= "SELECT " + COLUMN_ID + " FROM " + USER + " DESC LIMIT 1"

        var cursor = db.rawQuery(query, null)

        if(cursor.moveToNext()){
            // Toast.makeText(Context, cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_ID)), Toast.LENGTH_LONG).show()
        }
        try {
            var user = cursor.getString(cursor.getColumnIndex(COLUMN_ID)).toString()
            cursor.close()
            if (user.length == 1){
                return true
            }
            return false
        }
        catch (e: Exception){
            Toast.makeText(Context, e.toString(), Toast.LENGTH_LONG).show()
        }

        return false
    }


    fun insertProfil(id: Int, category: String, type: String, budget: Double) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_PROFILE_USER_ID, 1)
        cv.put(COLUMN_PROFILE_TYPE, type)
        cv.put(COLUMN_BUDGET, budget)
        cv.put(COLUMN_FAVORITE, category)

        val result = db.insert(PROFILE, null, cv)

        if (result == -1.toLong())
            Toast.makeText(Context, "Failed DBBBB", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(Context, "Success DBBB", Toast.LENGTH_LONG).show()
    }



    fun editProfil(id: Int, category: String, type: String, budget: Double) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_PROFILE_USER_ID, id)
        cv.put(COLUMN_PROFILE_TYPE, type)
        cv.put(COLUMN_BUDGET, budget)
        cv.put(COLUMN_FAVORITE, category)

        val query = "UPDATE " + PROFILE + " SET " + COLUMN_PROFILE_TYPE + " = '" + type + "' " +
                ", " + COLUMN_BUDGET + " = " + budget + ", " + COLUMN_PROFILE_USER_ID + " = " + id +
                " WHERE " + COLUMN_FAVORITE + " = '" + category + "'"

        var cursor = db.rawQuery(query, null)
        cursor.moveToFirst();
        cursor.close();

    }


    fun lookProfil(category: String): Boolean{
        val db = this.readableDatabase
        val query= "SELECT " + COLUMN_BUDGET + " FROM " + PROFILE + " WHERE " +
                COLUMN_FAVORITE + " = '" + category + "'"

        var cursor = db.rawQuery(query, null)

         var user = ""
        while(cursor.moveToNext()){
            user = cursor.getString(cursor.getColumnIndex(COLUMN_BUDGET)).toString()

            cursor.close()
            // Toast.makeText(Context, cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_ID)), Toast.LENGTH_LONG).show()
        }
        try {

            if (user.length != 0 ){
                return true
            }
            Toast.makeText(Context, "DOESN'T EXIST ", Toast.LENGTH_LONG).show()
            return false
        }
        catch (e: Exception){
            Toast.makeText(Context, "LOOK UP PROBEM "+ e.toString(), Toast.LENGTH_LONG).show()
        }
        return false

    }

    fun isProfilEmpty(): Boolean {

        val db = this.readableDatabase
        val query= "SELECT " + COLUMN_PROFILE_USER_ID + " FROM " + PROFILE + " DESC LIMIT 1"

        var cursor = db.rawQuery(query, null)

        if(cursor.moveToNext()){
            // Toast.makeText(Context, cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_ID)), Toast.LENGTH_LONG).show()
        }

        try {
            var user = cursor.getString(cursor.getColumnIndex(COLUMN_PROFILE_USER_ID)).toString()
            cursor.close()

            if (user.length > 0){
                return true
            }
            return false
        }
        catch (e: Exception){
            Toast.makeText(Context, e.toString(), Toast.LENGTH_LONG).show()
            return false
        }

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

    fun insertReceipt(total: Double, store: String){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_TOTAL, total)
        cv.put(COLUMN_STORE, store)

        val result = db.insert(RECEIPT, null, cv)



        if (result == -1.toLong())
            Toast.makeText(Context, result.toString(), Toast.LENGTH_LONG).show()
        else
            Toast.makeText(Context, "Success" + total.toString() + " "+ store, Toast.LENGTH_LONG).show()

    }

    fun insertContains(receiptID: Int, itemID: Int){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_CONTAINS_ITEM_ID, itemID)
        cv.put(COLUMN_CONTAINS_RECEIPT_ID, receiptID)

        val result = db.insert(CONTAINS, null, cv)

    }


    // function check if there is any notification having that id
    // Return true if there is one
    // Return false if there are not
    fun checkNotificationId(id: Int): Boolean{
        val db = this.readableDatabase
        val query= "SELECT " + COLUMN_DESCRIPTION + " FROM " + NOTIFICATION + " WHERE " +
                COLUMN_NOTIFICATION_ID + " = " + id + " DESC LIMIT 1"

        try{
            var cursor = db.rawQuery(query, null)

            if(cursor.moveToNext()){
                // Toast.makeText(Context, cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_ID)), Toast.LENGTH_LONG).show()
            }

            var user = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)).toString()
            cursor.close()

            if (user.length != 0 ){
                return true
            }
            return false
        }
        catch (e: Exception){
            return false
        }

    }

    fun updateNotification(id: Int, description: String){

        val db = this.writableDatabase
        val queryTemp =
                "UPDATE " + NOTIFICATION + " SET " + COLUMN_DESCRIPTION + " = '"+description+"'" +
                        " WHERE " + COLUMN_NOTIFICATION_ID + " = " + id

        val cursorTemp = db.rawQuery(queryTemp, null)
        cursorTemp.moveToFirst()
        cursorTemp.close()
    }

    fun insertNotification(id: Int, description: String){

        if(!checkNotificationId(id)){
            val db = this.writableDatabase
            val cv = ContentValues()
            cv.put(COLUMN_DESCRIPTION, description)
            cv.put(COLUMN_NOTIFICATION_ID, id)

            val result = db.insert(NOTIFICATION, null, cv)

        }
        else{
            updateNotification(id, description)
        }

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


    fun getCategoryName(categoryID: Int): String{

        val db = this.readableDatabase
        val query= "SELECT " + COLUMN_CATEGORY_NAME + " FROM " + CATEGORY + " WHERE " + COLUMN_CATEGORY_ID + " = " +
                categoryID + " ORDER BY " + COLUMN_CATEGORY_NAME + " DESC LIMIT 1"

        var cursor = db.rawQuery(query, null)

        if(cursor.moveToNext()){
            // Toast.makeText(Context, cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_ID)), Toast.LENGTH_LONG).show()
        }
        var category = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_NAME))
        cursor.close()

        return category

    }

    fun getCatTotalCost(): MutableList<Double> {
        val categories = getCategories()

        val list = mutableListOf<Double>()
        var total = 0.0
        val itemID = mutableListOf<Int>()
        var query: String

        val db = this.readableDatabase
        for (x in categories) {
            query =
                    "SELECT $COLUMN_BELONG_ITEM_ID FROM $BELONG WHERE $COLUMN_BELONG_CATEGORY_ID = ${
                    getCategoryID(x)
                    }"
            val cursor = db.rawQuery(query, null)
            while (cursor.moveToNext()) {
                itemID.add(cursor.getInt(cursor.getColumnIndex(COLUMN_BELONG_ITEM_ID)))
            }
            cursor.close()
            for (y in itemID) {
                query = "SELECT $COLUMN_PRICE FROM $ITEM WHERE $COLUMN_ITEM_ID = $y"
                val cursor2 = db.rawQuery(query, null)
                while (cursor2.moveToNext()) {
                    total += cursor2.getDouble(cursor2.getColumnIndex(COLUMN_PRICE))
                }
                cursor2.close()
            }
            list.add(total)
            total = 0.0
            itemID.clear()
        }

        return list
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



    fun getItemCategoryInBelong(id: Int): Int{

        val db = this.readableDatabase
        val query = "SELECT " + COLUMN_BELONG_CATEGORY_ID + " FROM " + BELONG + " WHERE " +
                COLUMN_BELONG_ITEM_ID + " = "+ id + " ORDER BY " + COLUMN_BELONG_CATEGORY_ID + " LIMIT 1"

        var cursor = db.rawQuery(query, null)

        if(!cursor.moveToNext()){
            // Toast.makeText(Context, "category "+ category + " doesn't exist", Toast.LENGTH_LONG).show()
        }

        var id = cursor.getString(cursor.getColumnIndex(COLUMN_BELONG_CATEGORY_ID)).toInt()
        Toast.makeText(Context,"category id in belong is " + id, Toast.LENGTH_LONG).show()
        return id
    }

    fun getItemsWithID(id: Int): MutableList<Item> {
        val db = this.readableDatabase

        //get the Item ID's for this receipt
        val query =
            "SELECT $COLUMN_CONTAINS_ITEM_ID FROM $CONTAINS WHERE $COLUMN_CONTAINS_RECEIPT_ID = $id"

        val cursor = db.rawQuery(query, null)
        val idList = mutableListOf<Int>()
        while (cursor.moveToNext()) {
            idList.add(cursor.getInt(cursor.getColumnIndex(COLUMN_CONTAINS_ITEM_ID)))
        }

        cursor.close()

        //get the items and return them to the activity
        val itemList = mutableListOf<Item>()
        for (x in idList) {
            val query2 = "SELECT * FROM $ITEM WHERE $COLUMN_ITEM_ID = $x"
            val cursor2 = db.rawQuery(query2, null)
            if (!cursor2.moveToNext())
                throw IllegalAccessException("Item Does not Exist")
            val iName = cursor2.getString(1)
            val iPrice = cursor2.getDouble(2)
            val iAmount = cursor2.getInt(3)
//            cursor2.close()
            val query3 =
                "SELECT $COLUMN_BELONG_CATEGORY_ID FROM $BELONG WHERE $COLUMN_BELONG_ITEM_ID = $x"
            val cursor3 = db.rawQuery(query3, null)
            if (!cursor3.moveToNext())
                throw IllegalAccessException("Item Does Not Exist")
            var iCat = getCategoryName(
                cursor3.getInt(
                    cursor3.getColumnIndex(
                        COLUMN_BELONG_CATEGORY_ID
                    )
                )
            )
//            cursor3.close()
            itemList.add(Item(x, iName, iPrice, iAmount, iCat))

        }
        return itemList
    }

    data class Item(
            val itemID: Int,
            var itemName: String,
            var itemPrice: Double,
            var itemAmount: Int,
            var itemCategory: String
    ): Serializable






    fun getItemsOfCategory(category: String): MutableList<String> {

        var categoryFound = getCategoryID(category)

        var list: MutableList<String> = ArrayList()

        val db = this.readableDatabase
        val query= "Select I." + COLUMN_ITEM_NAME + " FROM " + ITEM + " AS I, " + BELONG + " AS B WHERE B."+
                COLUMN_BELONG_ITEM_ID + " = I." + COLUMN_ITEM_ID +
                " AND B."+ COLUMN_BELONG_CATEGORY_ID + " = " + "'"+ categoryFound + "'"


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

                name= cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_NAME))

                list.add(name)
                //Toast.makeText(Context, name +" cost "+ price.toInt(), Toast.LENGTH_LONG).show()


            }while (cursor.moveToNext())

            db.close()
        }
        Toast.makeText(Context, "" + list.size+ " item(s) found ", Toast.LENGTH_LONG).show()
        return list

    }

    fun getPriceOfSingleItem(id: Int):Int{

        val db = this.readableDatabase
        val query= "SELECT " + COLUMN_PRICE + " FROM " + ITEM + " WHERE " + COLUMN_ITEM_ID + " = " +
                id + " ORDER BY " + COLUMN_PRICE + " DESC LIMIT 1"

        var cursor = db.rawQuery(query, null)

        if(cursor.moveToNext()){
            // Toast.makeText(Context, cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_ID)), Toast.LENGTH_LONG).show()
        }
        var price = cursor.getString(cursor.getColumnIndex(COLUMN_PRICE)).toInt()
        cursor.close()

        return price
    }

    fun getOnlyPriceOfItemOfCategory(category: String):MutableList<String>{

        var categoryFound = getCategoryID(category)

        var list: MutableList<String> = ArrayList()

        val db = this.readableDatabase
        val query= "Select I." + COLUMN_PRICE + " FROM " + ITEM + " AS I, " + BELONG + " AS B WHERE B."+ COLUMN_BELONG_ITEM_ID + " = I." + COLUMN_ITEM_ID +
                " AND B."+ COLUMN_BELONG_CATEGORY_ID + " = " + "'"+ categoryFound + "'"

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
            db.close()
        }
        //Toast.makeText(Context, "The price size "+ list.size, Toast.LENGTH_LONG).show()
        return list
    }

    fun getReceiptInfo(id: Int): Receipt {
        val db = this.readableDatabase
        val query = "SELECT * FROM $RECEIPT WHERE $COLUMN_RECEIPT_ID = $id"
        val cursor = db.rawQuery(query, null)
        if (!cursor.moveToNext()) {
            throw IllegalArgumentException("That Receipt Does Not Exist")
        }
        val price = cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTAL))
        //val tax = cursor.getDouble(cursor.getColumnIndex(COLUMN_TAX))
        val storeName = cursor.getString(cursor.getColumnIndex(COLUMN_STORE))
        return Receipt(storeName, price)
    }

    class Receipt(val storeName: String, val price: Double, val tax: Double = 0.0)




    fun getLastItemID():Int{

        val db = this.readableDatabase
        val query= "SELECT * FROM " + ITEM + " ORDER BY " + COLUMN_ITEM_ID + " DESC LIMIT 1"

        var cursor = db.rawQuery(query, null)

        if(cursor.moveToNext()){
            // Toast.makeText(Context, cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_ID)), Toast.LENGTH_LONG).show()
        }
        var id = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_ID)).toInt()
        cursor.close()
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


    fun getAll_Receipt_ID(): MutableList<Int>{

        var list: MutableList<Int> = ArrayList()
        val db = this.readableDatabase
        val query= "SELECT " + COLUMN_RECEIPT_ID + " FROM " + RECEIPT + " ORDER BY " + COLUMN_RECEIPT_ID + " DESC "
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException){
            db.execSQL(query)
            return ArrayList()
        }

        var id :Int

        if(cursor.moveToFirst()){
            do {

                id= cursor.getString(cursor.getColumnIndex(COLUMN_RECEIPT_ID)).toInt()
                list.add(id)
                //Toast.makeText(Context, name, Toast.LENGTH_LONG).show()

            }while (cursor.moveToNext())
        }

        return list
    }

    fun getAll_Total_Receipt_ID(): MutableList<String>{

        var list: MutableList<String> = ArrayList()
        val db = this.readableDatabase
        val query= "SELECT " + COLUMN_TOTAL + " FROM " + RECEIPT
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException){
            db.execSQL(query)
            return ArrayList()
        }

        var price :String

        if(cursor.moveToFirst()){
            do {

                price = cursor.getString(cursor.getColumnIndex(COLUMN_TOTAL)).toString()
                list.add(price)
                //Toast.makeText(Context, name, Toast.LENGTH_LONG).show()

            }while (cursor.moveToNext())
        }

        return list
    }


    fun getAll_Date_Receipt_ID(): MutableList<String>{

        var list: MutableList<String> = ArrayList()
        val db = this.readableDatabase
        val query= "SELECT " + COLUMN_DATE + " FROM " + RECEIPT
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException){
            db.execSQL(query)
            return ArrayList()
        }

        var price :String

        if(cursor.moveToFirst()){
            do {

                price = cursor.getString(cursor.getColumnIndex(COLUMN_DATE)).toString()
                list.add(price)
                //Toast.makeText(Context, name, Toast.LENGTH_LONG).show()

            }while (cursor.moveToNext())
        }

        return list
    }



    fun getReceiptDate(id: Int): String{

        val db = this.readableDatabase
        val query= "SELECT "+ COLUMN_DATE + " FROM " + RECEIPT + " WHERE " + COLUMN_RECEIPT_ID + " = " + id + " ORDER BY " +
                COLUMN_DATE + " LIMIT 1"

        var cursor = db.rawQuery(query, null)

        if(cursor.moveToNext()){
            // Toast.makeText(Context, cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_ID)), Toast.LENGTH_LONG).show()
        }
        var date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE)).toString()

        return date



    }

    fun getReceiptTotal_Price(id: Int): String{

        val db = this.readableDatabase
        val query= "SELECT "+ COLUMN_PRICE + " FROM " + RECEIPT + " WHERE " + COLUMN_RECEIPT_ID + " = " + id + " ORDER BY " +
                COLUMN_PRICE + " LIMIT 1"

        var cursor = db.rawQuery(query, null)

        if(cursor.moveToNext()){
            // Toast.makeText(Context, cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_ID)), Toast.LENGTH_LONG).show()
        }
        var price = cursor.getString(cursor.getColumnIndex(COLUMN_PRICE)).toString()

        return price
    }

    fun getNotification(): MutableList<String>{
        var list: MutableList<String> = ArrayList()
        val db = this.readableDatabase
        val query= "SELECT * FROM " + NOTIFICATION
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException){
            db.execSQL(query)
            return ArrayList()
        }

        if(cursor.moveToFirst()){
            do {

                var notify = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)).toString()
                var id = cursor.getString(cursor.getColumnIndex(COLUMN_NOTIFICATION_ID)).toString()
                list.add(id + ": " + notify.toString())
                //Toast.makeText(Context, name, Toast.LENGTH_LONG).show()

            }while (cursor.moveToNext())
        }

        return list
    }

    fun getNotificationNumber(): Int {

        var list: MutableList<String> = ArrayList()

        val db = this.readableDatabase
        val query= "SELECT " + COLUMN_NOTIFICATION_ID + " FROM " + NOTIFICATION
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException){
            db.execSQL(query)
            return 0
        }

        if(cursor.moveToFirst()){
            do {
                var id = cursor.getString(cursor.getColumnIndex(COLUMN_NOTIFICATION_ID)).toString()
                list.add(id)
            }while (cursor.moveToNext())
        }
        return list.size
    }



    fun getLastReceiptID():Int{

        val db = this.readableDatabase
        val query= "SELECT " + COLUMN_RECEIPT_ID + " FROM " + RECEIPT + " ORDER BY " + COLUMN_RECEIPT_ID + " DESC LIMIT 1"

        var cursor = db.rawQuery(query, null)

        if(cursor.moveToNext()){
            // Toast.makeText(Context, cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_ID)), Toast.LENGTH_LONG).show()
        }
        var id = cursor.getString(cursor.getColumnIndex(COLUMN_RECEIPT_ID)).toInt()
        cursor.close()
        return id
    }

    fun getProfile(): MutableList<String>{

        val db = this.readableDatabase
        val query= "SELECT * FROM " + PROFILE

        var cursor = db.rawQuery(query, null)

        if(cursor.moveToNext()){
            // Toast.makeText(Context, cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_ID)), Toast.LENGTH_LONG).show()
        }
        var type = cursor.getString(cursor.getColumnIndex(COLUMN_PROFILE_TYPE)).toString()
        var budget = cursor.getString(cursor.getColumnIndex(COLUMN_BUDGET)).toString()
        var favorite = cursor.getString(cursor.getColumnIndex(COLUMN_FAVORITE)).toString()

        var list: MutableList<String> = ArrayList()
        list.add(type)
        list.add(budget)
        list.add(favorite)

        return list
    }

    fun getAllProfiles(): MutableList<String> {

        var list: MutableList<String> = ArrayList()

        val db = this.readableDatabase
        val query= "SELECT * FROM " + PROFILE
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

                var type = cursor.getString(cursor.getColumnIndex(COLUMN_PROFILE_TYPE)).toString()
                var budget = cursor.getString(cursor.getColumnIndex(COLUMN_BUDGET)).toString()
                var favorite = cursor.getString(cursor.getColumnIndex(COLUMN_FAVORITE)).toString()
                var profile = "Type " + type + " in " + favorite + " Limit = $" + budget
                list.add(profile.toString())
                //Toast.makeText(Context, name, Toast.LENGTH_LONG).show()

            }while (cursor.moveToNext())
        }

        return list
    }


    fun getDetailProfiles(): MutableList<String> {

        var list: MutableList<String> = ArrayList()

        val db = this.readableDatabase
        val query= "SELECT * FROM " + PROFILE
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


                var budget = cursor.getString(cursor.getColumnIndex(COLUMN_BUDGET)).toString()
                var category = cursor.getString(cursor.getColumnIndex(COLUMN_FAVORITE)).toString()

                list.add(category.toString())
                list.add(budget)
                //Toast.makeText(Context, name, Toast.LENGTH_LONG).show()

            }while (cursor.moveToNext())
        }

        return list
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

    fun deleteItem(itemID: Int) {
        val query = "delete from $ITEM where $COLUMN_ITEM_ID = $itemID"
        val query2 = "delete from $CONTAINS where $COLUMN_CONTAINS_ITEM_ID = $itemID"
        val query3 = "delete from $BELONG where $COLUMN_BELONG_ITEM_ID = $itemID "
        val db = this.writableDatabase
        db.execSQL(query)
        db.execSQL(query2)
        db.execSQL(query3)
        db.close()
    }

    fun deleteNotification (position: String){

        val temp = position.split(":")
        val item = temp[0].toInt()

        val database = this.writableDatabase
        val query = "DELETE FROM "+ NOTIFICATION + " WHERE " + COLUMN_NOTIFICATION_ID + " = '"+item+"' "

        database.execSQL(query)
        database.close()

    }




    /*****************************************/
    /* FUNCTION OF UPDATING IN THE DATABASE */
    /****************************************/

    fun updateReceipt(receiptID: Int, receipt: Receipt): Boolean {
        //check to see if the receipt information has been updated
        val check = getReceiptInfo(receiptID)
        if (checkIfReceiptsAreSame(check, receipt)) {
            return false //the receipt info has not changed
        }
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_STORE, receipt.storeName)
        cv.put(COLUMN_TOTAL, receipt.price)

        val query =
                "UPDATE $RECEIPT SET $COLUMN_PRICE = ${receipt.price}, $COLUMN_STORE = ${receipt.storeName} WHERE $COLUMN_RECEIPT_ID = $receiptID"
        val cursor = db.rawQuery(query, null)
        if (!cursor.moveToFirst()) {
            throw IllegalArgumentException("Receipt ID Does Not Exist")
        }
        cursor.close()
        return true
    }

    private fun checkIfReceiptsAreSame(check: Receipt, toCheck: Receipt): Boolean {
        return when {
            !check.price.equals(toCheck.price) -> false
            check.storeName != toCheck.storeName -> false
            else -> true
        }
    }


    // This function is used to update an item in the database
    fun updateItem(itemName: String, price:Int, category: String, Itemid: Int){

        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_ITEM_NAME, itemName)
        cv.put(COLUMN_PRICE, price)

        //get the current category of the item
        val currentCategory = getItemCategoryInBelong(Itemid)
        val futureCategory= getCategoryID(category)

        //update the table item and close cursor
        val query= "UPDATE "+ ITEM + " SET "+ COLUMN_ITEM_NAME + " = '"+itemName+"' " +
                ", "+ COLUMN_PRICE + " = "+ price + " WHERE " + COLUMN_ITEM_ID + " = " + Itemid

        var cursor = db.rawQuery(query, null)
        cursor.moveToFirst();
        cursor.close();

        //Check if category has changed, if yes, then update
        if (!currentCategory.equals(futureCategory))
        {
            Toast.makeText(Context, "different category to update", Toast.LENGTH_LONG).show()

            val queryTemp= "UPDATE "+ BELONG + " SET "+ COLUMN_BELONG_CATEGORY_ID + " = " + futureCategory +
                    " WHERE " + COLUMN_BELONG_ITEM_ID + " = " + Itemid

            val cursorTemp = db.rawQuery(queryTemp, null)
            cursorTemp.moveToFirst()
            cursor.close()
        }
    }

    fun updateItem(itemName: String, price: Double, amount: Int, category: String, Itemid: Int) {

        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_ITEM_NAME, itemName)
        cv.put(COLUMN_PRICE, price)

        //get the current category of the item
        val currentCategory = getItemCategoryInBelong(Itemid)
        val futureCategory = getCategoryID(category)

        //update the table item and close cursor
        val query = "UPDATE " + ITEM + " SET " + COLUMN_ITEM_NAME + " = '" + itemName + "' " +
                ", " + COLUMN_PRICE + " = " + price + ", " + COLUMN_AMOUNT + "=" + amount + " WHERE " + COLUMN_ITEM_ID + " = " + Itemid

        var cursor = db.rawQuery(query, null)
        cursor.moveToFirst();
        cursor.close();

        //Check if category has changed, if yes, then update
        if (!currentCategory.equals(futureCategory)) {
            //Toast.makeText(Context, "different category to update", Toast.LENGTH_LONG).show()

            val queryTemp =
                    "UPDATE " + BELONG + " SET " + COLUMN_BELONG_CATEGORY_ID + " = " + futureCategory +
                            " WHERE " + COLUMN_BELONG_ITEM_ID + " = " + Itemid

            val cursorTemp = db.rawQuery(queryTemp, null)
            cursorTemp.moveToFirst()
            cursor.close()
        }
    }

}