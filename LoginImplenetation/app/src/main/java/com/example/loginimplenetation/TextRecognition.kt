package com.example.loginimplenetation


import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginimplenetation.databinding.TextRecognitionActivityBinding
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import java.io.File

class TextRecognition : AppCompatActivity() {

    private lateinit var binding: TextRecognitionActivityBinding

    override fun onCreate(savedInstnceState: Bundle?) {
        super.onCreate(savedInstnceState)
        binding = TextRecognitionActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button2 = findViewById<Button>(binding.button2.id)
        val button3 = findViewById<Button>(binding.button3.id)

        button2.setOnClickListener {
            val file = File("Test.jpeg")
            if(file.exists()){ //if the file exists
                val myBitmap = BitmapFactory.decodeFile(file.absolutePath)
                val image = ImageDecoder.d
                val input = InputImage.fromFilePath()
                recognizeText(input) //convert the image into a bitmap and then pass it to the MLA
            }

        }

        button3.setOnClickListener{
            finish()
        }
    }

    fun recognizeText(image: InputImage) {
        //init detector_default
        val recognizer = TextRecognition.getClient()


        //run the detector
        val result = recognizer.process(image).addOnSuccessListener { visionText ->

            for (block in visionText.textBlocks) {
                val boundingBox = block.boundingBox
                val cornerPoints = block.cornerPoints


                binding.textView.setText(block.text)
            }
        }
            .addOnFailureListener { e ->
                Toast.makeText(
                    this,
                    "Failed to read text, please try again",
                    Toast.LENGTH_SHORT
                ) //let the user know that there was an error and to try again
            }


    }


    private fun processTextBlock(result: Text) {
        //start ML Kit Text Block Processing
        val resultText = result.text
        for (block in result.textBlocks) {
            val blockText = block.text
            val blockCornerPoints = block.cornerPoints
            val blockFrame = block.boundingBox
            for (line in block.lines) {

            }
        }
    }
}