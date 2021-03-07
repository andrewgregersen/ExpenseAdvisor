package com.example.loginimplenetation

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.loginimplenetation.R
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class  Camera_Activity : AppCompatActivity() {

    var currentPath: String? = null
    val TAKE_PICTURE = 1
    val SELECT_PICTURE = 2
    private val cameraRequestId = 1222
    val MY_CAMERA_REQUEST_CODE = 100;

//    companion object{
//        private const val CAMERA_PERMISSION_CODE= 1
//        private const val CAMERA_REQUEST_CODE = 2
//    }

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_)

        buttonGallery.setOnClickListener {
           dispatchGalleryIntent()
        }

        buttonCamera.setOnClickListener {
           dispatchCameraIntent()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == TAKE_PICTURE && resultCode== Activity.RESULT_OK){

            val bitmap: Bitmap = BitmapFactory.decodeFile(currentPath)
            imageView.setImageBitmap(bitmap)

            //************************ need to pass the image to machine learning here. ******************************//
            var btn: Button = findViewById(R.id.submitIt)
            btn.setOnClickListener {
                var intent: Intent = Intent(this, TextRecognitionActivity::class.java)
                intent.putExtra("data", bitmap)
                startActivity(intent)

            }
        }

        if(requestCode == SELECT_PICTURE && resultCode== Activity.RESULT_OK){
            try{
                val uri= data!!.data
                imageView.setImageURI(uri)

            }catch (e: IOException ){
                e.printStackTrace()
            }
        }
    }

    fun dispatchGalleryIntent(){
      val intent= Intent()
        intent.type= "image/*"
        intent.action= Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select image"), SELECT_PICTURE)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun dispatchCameraIntent(){

        // if (checkSelfPermission(Manifest.permission.CAMERA)
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),
            MY_CAMERA_REQUEST_CODE)
        }

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
             == PackageManager.PERMISSION_GRANTED) {

            var timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val fileName = "JPEG_"+timeStamp+"_"
            val strorageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            try {
                val imageFile = File.createTempFile(fileName, ".jpg", strorageDirectory)
                currentPath = imageFile.getAbsolutePath()
                val imageUri:Uri = FileProvider.getUriForFile (this, "com.example.myapplication.fileprovider", imageFile)
                val intent=  Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                startActivityForResult(intent, TAKE_PICTURE)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}

