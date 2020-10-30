package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

// Database class extends SQLiteOpenHelper
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";

    //Constructor
    public DataBaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    // Call first time when to try access the database object
    //Should be code that generate table
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CreateTableStatement= "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_AGE + " INT, " + COLUMN_ACTIVE_CUSTOMER + " BOOL)";

        db.execSQL(CreateTableStatement);

    }



    //Call when version of database change
    //prevent previous version to crash
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Method to add a value into the DB
    public boolean addOne(CustomerModel customerModel){
        //it gonna write into the database
        SQLiteDatabase db= this.getReadableDatabase();
        ContentValues cv= new ContentValues(); //like a HashMap (name and contenet)

        cv.put(COLUMN_CUSTOMER_NAME, customerModel.getName());
        cv.put(COLUMN_CUSTOMER_AGE, customerModel.getAge());
        cv.put(COLUMN_ACTIVE_CUSTOMER, customerModel.isActive());
        //no ID because it's autoincrement;

        long insert = db.insert(CUSTOMER_TABLE, null ,cv);

        if(insert == -1){
            return false;
        }
        else{
            return true;
        }

    }

    public List<CustomerModel> getEveryone(){
        List<CustomerModel> returnList= new ArrayList<>();
       //code that get data from DB
        String queryString= "SELECT * FROM "+ CUSTOMER_TABLE;

        SQLiteDatabase db= this.getReadableDatabase();
       //cursor is like array of data
        Cursor cursor = db.rawQuery(queryString, null );

        if(cursor.moveToFirst()){
            //if there are still something to read
            do{
                //precise the type to get and the order of the column it is found in the database
                int customerID= cursor.getInt(0);
                String customerName= cursor.getString(1);
                int customerAge= cursor.getInt(2);
                boolean customerActive= cursor.getInt(3)==1? true: false; // means if ==1 it's true, otherwise it's false;

                //create a new entity
                CustomerModel newCustomer= new CustomerModel(customerID, customerName, customerAge, customerActive);
                returnList.add(newCustomer);
            }while(cursor.moveToNext()); //as long as there is still some

        }
        else{
          //failure, do not add to the list
        }

        cursor.close(); //close cursor
        db.close();   //close database
        return returnList;
    }

    public boolean deleteOne(CustomerModel customerModel){
        //to delete a item by just clicking on it

        SQLiteDatabase db= this.getWritableDatabase();
        String queryString= "DELETE FROM "+ CUSTOMER_TABLE + " WHERE "+ COLUMN_ID + " = " + customerModel.getId();
        Cursor cursor= db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }

    }
}
