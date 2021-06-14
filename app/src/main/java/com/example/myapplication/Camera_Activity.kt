package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_camera_.*
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
//           val images:Bitmap = data?.extras?.get("data") as Bitmap
//            imageView.setImageBitmap(images)


        //            try{
////                //From previous method dispatchCameraIntent
////                val file = File(currentPath)
////                val uri= Uri.fromFile(file)
////                imageView.setImageURI(uri)
//
//                //From method cameraTest
////                val thumBnail: Bitmap = data!!.extras!!.get("data") as Bitmap
////                imageView.setImageBitmap(thumBnail)
//
//                imageView.rotation = 90f
//                imageView.setImageURI(Uri.parse(currentPath))
//
//            }catch (e: IOException ){
//                e.printStackTrace()
//            }

            val bitmap: Bitmap = BitmapFactory.decodeFile(currentPath)
            imageView.setImageBitmap(bitmap)












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



//    fun createImage(): File?{
//        var timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
//        val imageName= "JPEG_"+timeStamp+"_"
//
//       // val fileName = "MyPicture"
//        var storageDir= getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//        var image= File.createTempFile(imageName, ".jpg", storageDir)
//        currentPath= image.absolutePath
//        return image
//    }

//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if(requestCode == CAMERA_PERMISSION_CODE){
//            if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
//                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                startActivityForResult(intent, CAMERA_REQUEST_CODE)
//            }
//            else{
//                Toast.makeText(this, "Permission denail", Toast.LENGTH_LONG).show()
//            }
//
//
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun dispatchCameraIntent(){
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//
//        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
//                == PackageManager.PERMISSION_GRANTED) {
//            //startActivityForResult(intent, TAKE_PICTURE)
//
//            if (intent.resolveActivity(packageManager) != null) {
//                var photoFile: File? = null
//                try {
//                    photoFile = createImage()
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//
//                if (photoFile != null) {
//                    // you must create a content provider matching the authority
//                    var photoUri = FileProvider.getUriForFile(
//                            this,
//                            "com.camera.app.fileprovider", photoFile
//                    )
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
//                    startActivityForResult(intent, cameraRequestId)
//                }
//
//            }
//        } else {
//            //request permission
//            ActivityCompat.requestPermissions(
//                    this,
//                    arrayOf(android.Manifest.permission.CAMERA), TAKE_PICTURE
//            )
//        }
//
//    if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
 //               == PackageManager.PERMISSION_GRANTED) {


///////////////////////////
        //if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
         //      == PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),
//            cameraRequestId)
//            val cameraInt= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            startActivityForResult(cameraInt, cameraRequestId)

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

//    fun cameraTest(){
//
//        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
//            == PackageManager.PERMISSION_GRANTED
//        ) {
//            //use camera functionality
//            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            startActivityForResult(intent, CAMERA_REQUEST_CODE)
//        } else {
//            //request permission
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(android.Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE
//            )
//        }
//    }