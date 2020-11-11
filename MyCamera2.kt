package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    val CAMERA_REQUEST_CODE = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            im_View.setOnClickListener {
            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(callCameraIntent.resolveActivity(packageManager)!=null){
                startActivityForResult(callCameraIntent,CAMERA_REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            CAMERA_REQUEST_CODE -> {
                if(resultCode== Activity.RESULT_OK && data != null){
                    im_View.setImageBitmap(data.extras?.get("data") as Bitmap)//get("data") as Bitmap)
                }
            }
            else -> {
                Toast.makeText(this,"Unreconignized request code", Toast.LENGTH_SHORT).show()
            }
        }
    }
}