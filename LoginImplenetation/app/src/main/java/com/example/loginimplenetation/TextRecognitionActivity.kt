package com.example.loginimplenetation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import java.io.IOException
import java.io.InputStream



//AdapterView.OnItemSelectedListener for spinner
class TextRecognitionActivity: AppCompatActivity() {
    private final val TAG = "TextRecognitionActivity"
    private var mSelectedImage: Bitmap? = null
    private lateinit var doTheThing: Button
    private lateinit var leave: Button
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: RecyclerView.Adapter<*>
    //need to implement a recycler view to display the data


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.text_recognition_activity)



        mSelectedImage = getBitmapFromAsset(this, "testR.jpg")

        manager = LinearLayoutManager(this)

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
        var values = arrayListOf<String>()
        var str = ""
        for(i in blocks){
            var lines = i.lines //returns a list of lines in the block
            for(j in lines){
                var elements = j.elements //returns a list of elements in the line
                str =""
                for(k in elements){
                    str = "$str ${k.text}"
                    //this gives text item by item seperated on " ".
                }
                values.add(str)
            }
        }
        myAdapter = MyAdapter(values.toTypedArray())
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply{
            layoutManager = manager
            adapter = myAdapter
        }


    }
/*

    override fun onItemSelected(parent: AdapterView<*>?, v: View, position: Int, id: Long){
        when(position){
            0 -> mSelectedImage = getBitmapFromAsset(this, "download.jpg")
            1 -> mSelectedImage = getBitmapFromAsset(this, "Test.jpg")
        }
    }

*/
    private fun getBitmapFromAsset(context: Context, filePath: String?) : Bitmap{
        val assetManager = context.assets
        val `is`: InputStream
        var bitmap : Bitmap? = null
        try {
            `is` = assetManager.open(filePath!!)
            bitmap = BitmapFactory.decodeStream(`is`)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bitmap!!
    }


 /*
    override fun onNothingSelected(parent: AdapterView<*>){
        //do nothing
    }

  */





}


class MyAdapter(private val myDataSet: Array<String>): RecyclerView.Adapter<MyAdapter.ViewHolder>(){
    class ViewHolder(private val view: View):RecyclerView.ViewHolder(view){
    fun bind(text: String){
        val tv = view.findViewById<TextView>(R.id.textView)
        tv.text = text

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val vh = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent,false)
        return ViewHolder(vh)
    }
    override fun onBindViewHolder(holder: ViewHolder,position:Int){
        holder.bind(myDataSet[position])
    }
    override fun getItemCount(): Int{
        return myDataSet.size
    }
}