package com.example.loginimplenetation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import java.io.IOException
import java.io.InputStream


class TextRecognitionActivity: AppCompatActivity(), AdapterView.OnItemSelectedListener{
    private final val TAG = "TextRecognitionActivity"
    private var mSelectedImage: Bitmap? = null
    private lateinit var doTheThing: Button
    private lateinit var leave: Button
    private lateinit var textView: TextView
    //need to implement a recycler view to display the data


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.text_recognition_activity)

        val dropdown = findViewById<Spinner>(R.id.spinner)

        dropdown.adapter = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_dropdown_item, arrayOf(
                "TestImage1",
                "TestImage2"
            )
        )
        dropdown.onItemSelectedListener

        textView = findViewById<TextView>(R.id.textView)

        doTheThing = findViewById<Button>(R.id.button2)
        leave = findViewById<Button>(R.id.button3)

        doTheThing.setOnClickListener{
            runTextRecognition()
        }

        leave.setOnClickListener {
            finish()
        }

    }


    private fun runTextRecognition(){
        val image = InputImage.fromBitmap(mSelectedImage, 0)
        val recognizer = TextRecognition.getClient()
        doTheThing.isEnabled = false
        recognizer.process(image)
            .addOnSuccessListener{ texts ->
                doTheThing.isEnabled = true
                processTextRecognition(texts)
            }
    }




    private fun processTextRecognition(texts: Text){
        var blocks = texts.textBlocks
        if(blocks.size == 0) {
            Toast.makeText(this, "No text found", Toast.LENGTH_LONG).show()
            return
        }
        for(i in blocks){
            var lines = i.lines
            for(j in lines){
                var elements = j.elements
                for(k in elements){
                    textView.text = k.text //replace with adding it to a list or doing something with the data (maybe a list of arrays for each individual category that the DB has
                    //will need to parse data for the recycler view and then display it on it for manual editing (Category, Amount, Item Name, Price)
                    //Category will need to be auto populated from a dictionary based upon the Item name, will have to make a function to populate it
                    //add the Category to the list of parsed lines
                    Thread.sleep(3000) //show me the text, not needed for final testing
                }
            }
        }
    }


    override fun onItemSelected(parent: AdapterView<*>?, v: View, position: Int, id: Long){
        when(position){
            0 -> mSelectedImage = getBitmapFromAsset(this, "download.png")
            1 -> mSelectedImage = getBitmapFromAsset(this, "Please_walk_on_the_grass.jpg")
        }
    }


    fun getBitmapFromAsset(context: Context, filePath: String?): Bitmap? {
        val assetManager = context.assets
        val `is`: InputStream
        var bitmap : Bitmap? = null
        try {
            `is` = assetManager.open(filePath!!)
            bitmap = BitmapFactory.decodeStream(`is`)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bitmap
    }

    override fun onNothingSelected(parent: AdapterView<*>){
        //do nothing
    }






}