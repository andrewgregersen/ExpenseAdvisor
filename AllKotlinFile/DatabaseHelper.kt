package com.example.mainpage.Adapter

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.view.View
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
                COLUMN_ITEM_NAME + " VARCHAR(256)," + COLUMN_PRICE +" INTEGER)"
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
                "REFERENCES "+ CATEGORY + "("+ COLUMN_CATEGORY_ID + "))"
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

    fun insertcat() {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_CATEGORY_NAME, "one")

        val result = db.insert(CATEGORY, null, cv)

        if (result == -1.toLong())
            Toast.makeText(Context, "Failed", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(Context, "Success", Toast.LENGTH_LONG).show()
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


}