package com.example.loginimplementation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.text.HtmlCompat
import com.example.loginimplementation.Adapter.DatabaseHelper

class faq : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)





        val listView = findViewById<ListView>(R.id.listfaq)

        val string1 = "What is Expense Advisor?\n" +
                "\n" +
                "Expense Advisor is a receipt management app useful for purpose such as expenditure " +
                "tracking, taxes and to get some spending insights. Although our financial institutions(bank) " +
                "can show an aggregate view of each receipt amount, it does not offer an item level overview. \n" +
                "\n" +
                "Expense advisor is an app that can read from receipt sand manage to process, " +
                "categorize expenses, and may offer recommendations based on personal choices " +
                "and preferences. "

        val string2= "How do I start using Expense Advisor? \n" +
                "\n" +
                "Once you download the app on your android device, click on the app’s " +
                "icon and log into your account if you already have one. If not, you would have " +
                "to create your account using your Gmail, Facebook or any other email account " +
                "and get access to all the features of the app. "

        val string3 = "How does Expense Advisor give recommendations? \n" +
                "\n" +
                "Based on customers pre-defined choices and based on customers preferences and habit " +
                "purchase, Expense Advisor is capable to provide a variety of recommendation such as " +
                "stores, single items or groups of items, and days to make purchases. The more the " +
                "customer provides data from receipts, the better the application would update its " +
                "recommendations. \n" +
                "\n" +
                "In addition, Expense Advisor can send alerts if the limit of expense in a certain category " +
                "has been reached. "


        val string4 = "How frequent should I check my expenses? \n" +
                "\n" +
                "It is up to the customers to define how frequently they want to check the statistics" +
                " of their expenses registered since the application provides real-time statistics for a per day, " +
                "monthly, " +
                "weekly, or even a by category article and by preference of store as well. "

        val string5 = "How do I enter receipts information in the application? \n" +
                "\n" +
                "Expense Advisor offers two ways to enter information. The fastest one is to simply scan the " +
                "receipt using the camera of your phone and the application will manage getting all the necessary " +
                "details from the receipt. " +
                "The second way is for the user to simply enter the information manually into the application. "

        val string6 = "Internal FAQs \n" +
                "\n" +
                " \n" +
                "\n" +
                "How is Expense Advisor different from other similar applications? \n" +
                "\n" +
                "Expense Advisor does not only list details from receipts, but actually " +
                "plays the role of a financial management planner based on the users profile and " +
                "updates from data provided to the application. A completed analysis would result on " +
                "recommendations and alerts to help the users better manage their expenses. "

        val string7= "How is Expense advisor different from financial institutions(banks)? \n" +
                "\n" +
                "Financial institutions(banks) can show an aggregate view of each receipt amount, " +
                "but it does not offer an item level overview. Expense Advisor breaks down all expenses, " +
                "taxes and gives clear statistic, groups the expenses based on categories. The user can generate " +
                "reports and visual presentations of his expenses based on the transaction date of the expenses or " +
                "category of items. Expense Advisor has several pre-determined options where the users personal investment " +
                "portfolio is typically decided after answering several simple age and risk tolerance related questions. "



        val context = this
        val db = DatabaseHelper(context)
        var faq= arrayOf(string1, string2, string3, string4, string5, string6, string7)

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, faq)

        listView.adapter= arrayAdapter
    }
}