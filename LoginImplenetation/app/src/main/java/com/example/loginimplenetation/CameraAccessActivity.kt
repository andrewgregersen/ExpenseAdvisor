package com.example.loginimplenetation

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.loginimplenetation.R
import kotlinx.android.synthetic.main.camera_access_activity.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class CameraAccessActivity : AppCompatActivity() {

    var currentPath: String? = null
    val TAKE_PICTURE = 1
    val SELECT_PICTURE = 2

    companion object{
        private const val CAMERA_PERMISSION_CODE= 1
        private const val CAMERA_REQUEST_CODE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.camera_access_activity)

        buttonGallery.setOnClickListener {
            dispatchGalleryIntent()
        }

        buttonCamera.setOnClickListener {
            cameraTest()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == TAKE_PICTURE && resultCode== Activity.RESULT_OK){
            try{
                //From previous method dispatchCameraIntent
                val file = File(currentPath)
                val uri= Uri.fromFile(file)
                imageView.setImageURI(uri)

                //From method cameraTest
                val thumBnail: Bitmap = data!!.extras!!.get("data") as Bitmap
                imageView.setImageBitmap(thumBnail)


            }catch (e: IOException ){
                e.printStackTrace()
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

    fun dispatchCameraIntent(){

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(packageManager) != null) {
                var photoFile: File? = null
                try {
                    photoFile = createImage()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                if (photoFile != null) {
                    // you must create a content provider matching the authority
                    var photoUri = FileProvider.getUriForFile(
                        this,
                        "com.example.mainpage.fileprovider", photoFile
                    )
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                    startActivityForResult(intent, TAKE_PICTURE)
                }

            }
        }
    }

    fun createImage(): File{
        var timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageName= "JPEG_"+timeStamp+"_"
        var storageDir= getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        var image= File.createTempFile(imageName, ".jpg", storageDir)
        currentPath= image.absolutePath
        return image
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CAMERA_PERMISSION_CODE){
            if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            }
            else{
                Toast.makeText(this, "Permission denail", Toast.LENGTH_LONG).show()
            }


        }
    }

    fun cameraTest(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED
        ) {
            //use camera functionality
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        } else {
            //request permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE
            )
        }
    }
}