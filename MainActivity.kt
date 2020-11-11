package com.example.mycameraapplication
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.karumi.dexter.Dexter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.jar.Manifest

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.widget.Toast
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
//import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        val MY_CAMERA_REQUEST_CODE = 8484  // any number you want
    }

    var imageUri:Uri= null!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Event
        btn_camera.setOnClickListener{
            Dexter.withContext(this)
                .withPermissions(Arrays.asList(

                     android.Manifest.permission.READ_EXTERNAL_STORAGE,
                     android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                      android.Manifest.permission.CAMERA




                )).withListener(object:MultiplePermissionsListener{
                        override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                            if (p0!!.areAllPermissionsGranted()) {
                                val values = ContentValues()
                                values.put(MediaStore.Images.Media.TITLE, "NEW PICTURE")
                                values.put(MediaStore.Images.Media.DESCRIPTION, "From yor Camera")
                                imageUri= contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!
                                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                                startActivityForResult(intent, MY_CAMERA_REQUEST_CODE)

                            }
                            else{
                                Toast.makeText(this@MainActivity, "You must accept all the permissin", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onPermissionRationaleShouldBeShown(p0: MutableList<PermissionRequest>?, p1: PermissionToken?) {
                            TODO("Not yet implemented")
                        }
                    })







       }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == MY_CAMERA_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                try{
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri!!)
                    imageView.setImageBitmap(bitmap)
                }catch(e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }

}