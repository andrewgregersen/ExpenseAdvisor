package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Step1: Make reference to all buttons in the layout
    Button btn_add, btn_viewAll;
    EditText et_name, et_age;
    Switch sw_activeCustomer;
    ListView lv_customerList;

    ArrayAdapter customerArrayAdapter; //Help to display the listView
    DataBaseHelper dataBaseHelper; //Class of database

    //List<CustomerModel> everyone= dataBaseHelper.getEveryone();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Step2: Assign value on the on created where all un at the start of the app
        // with findViewByID; R is the resource; and the id defined in Layout
        btn_add= findViewById(R.id.btn_add);
        btn_viewAll= findViewById(R.id.btn_viewAll);
        et_age= findViewById(R.id.et_age);
        et_name= findViewById(R.id.et_name);
        sw_activeCustomer= findViewById(R.id.sw_active);
        lv_customerList= findViewById(R.id.lv_customerList);

        dataBaseHelper= new DataBaseHelper(MainActivity.this);


        //ArrayAdapter to display the list into the ListView
        ShowCustomerOnListView(dataBaseHelper);


        //Step3 Set up the Listener for all button
        //Step4 Create the DB "DataBaseHelper.java"class
        //each button would have its mini-class

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomerModel customerModel;
                try {
                    customerModel = new CustomerModel(-1, et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()), sw_activeCustomer.isChecked());
                    //step3.2 toast to see if button works
                    Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error Creating Customer", Toast.LENGTH_SHORT).show();
                    customerModel= new CustomerModel(-1, "error", 0,  false);
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean success= dataBaseHelper.addOne(customerModel);
                Toast.makeText(MainActivity.this, "Sucess= "+ success, Toast.LENGTH_SHORT).show();


                // if I want it to appear when I click add.
                // ShowCustomerOnListView(dataBaseHelper);
            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBaseHelper dataBaseHelper= new DataBaseHelper(MainActivity.this);
                //List<CustomerModel> everyone= dataBaseHelper.getEveryone();
                //Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();

                //ArrayAdapter to display the list into the ListView
                ShowCustomerOnListView(dataBaseHelper);


            }
        });

        //methode to do when clicking on a item of the list ( ex: delete)
        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerModel clickedCustomer= (CustomerModel) parent.getItemAtPosition(position); // the position of the item in the list
                dataBaseHelper.deleteOne(clickedCustomer);
                ShowCustomerOnListView(dataBaseHelper);
                Toast.makeText(MainActivity.this, "Deleted "+ clickedCustomer.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        }

    //Method that display the list into the ListView
    private void ShowCustomerOnListView(DataBaseHelper dataBaseHelper2) {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getEveryone());
        lv_customerList.setAdapter(customerArrayAdapter);
    };

}
