package com.example.loginimplenetation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        googleLogin.setOnClickListener{

        }

        Login.setOnClickListener {
            if(checkRegister(Username.text.toString(), Password.text.toString())) //if login is successful
                setContentView(R.layout.main_fragment) //load user profile

        }


    }

    class MainFragment : Fragment(){
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
            return inflater.inflate(R.layout.main_fragment, container, false)
        }
    }








   private fun checkRegister(username: String, password: String): Boolean{
       /*
       Ask nezi to implemnt this part since he is building the DB and will know more about it.
        */

       return if(username != null)
           password !=null;
       else false;

   }



}