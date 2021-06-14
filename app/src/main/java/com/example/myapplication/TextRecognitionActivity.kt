package com.example.loginimplenetation

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
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
//Mostly Written by Andrew Gregersen for Senior Capstone at Bellevue College

//AdapterView.OnItemSelectedListener for spinner
class TextRecognitionActivity: AppCompatActivity() {
    private final val TAG = "TextRecognitionActivity"
    private var mSelectedImage: Bitmap? = null //will be initalized with the image passed to the activity from camera
    private lateinit var doTheThing: Button
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: RecyclerView.Adapter<*>
    private lateinit var values: ArrayList<String>
    //need to implement a recycler view to display the data


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.text_recognition_activity)


        //mSelectedImage = For Nesi to figure out
       // mSelectedImage = getBitmapFromAsset(this, "testR.jpg")
        var i: Intent = getIntent()
        mSelectedImage= i.getParcelableExtra("data")

        manager = LinearLayoutManager(this)

        doTheThing = findViewById(R.id.button2)
        val leave = findViewById<Button>(R.id.button3)
        val submit = findViewById<Button>(R.id.Submit)


        //for testing
        doTheThing.setOnClickListener{
            runTextRecognition()
        }
        //cancel upload of receipt
        leave.setOnClickListener {
            finish()
        }

        //submit finalized item list (after allowing for editing)
        submit.setOnClickListener{
            submitItems()
        }

    }


    private fun runTextRecognition(){
        val image = InputImage.fromBitmap(mSelectedImage, 0)
        val recognizer = TextRecognition.getClient()

        doTheThing.isEnabled = false //removed for final release
        recognizer.process(image)
            .addOnSuccessListener{ texts ->
                doTheThing.isEnabled = true
                processTextRecognition(texts)
            }
            .addOnFailureListener{
                Toast.makeText(this, "Failed to read text", Toast.LENGTH_SHORT).show()
            }
    }




    private fun processTextRecognition(texts: Text){
        var blocks = texts.textBlocks
        if(blocks.size == 0) {
            Toast.makeText(this, "No text found", Toast.LENGTH_LONG).show()
            return
        }
        val tree = loadTree()
        values = arrayListOf()



        var str = ""
        var prev: Rect? = null
        for(i in blocks){
            println(i.boundingBox)

            if(prev == null){
                prev = i.boundingBox
                for(j in i.lines)
                    for(k in j.elements){
                        str="$str ${k.text.trim()}"
                    }
            }
            else if((-4<i.boundingBox?.top?.minus(prev.top)!!) && (10>i.boundingBox?.top?.minus(prev.top)!!)) {
                for (j in i.lines) {//the blocks are on the same line within a small margin of error
                    if (prev != null) {
                        if (i.boundingBox?.left!! < prev.left!!) { //if current bounding box's left most border is at a x-loc smaller than the previous ones
                            var temp = ""
                            for (k in j.elements) {
                                if (!tree.contains(k.text.toLowerCase().trim()))
                                    temp = "$temp ${k.text.trim()}"
                            }
                            str = "$temp $str" //place the items before the current string
                        } else {//continue as normal
                            for (j in i.lines) {
                                for (k in j.elements) {
                                    if (!tree.contains(k.text.toLowerCase().trim()))
                                        str = "$str ${k.text.trim()}"
                                }
                            }
                        }
                    }
                    prev = i.boundingBox //save the previous bounding box
                }
            }

            else{//blocks are not on the same line

                prev = i.boundingBox
                for(j in i.lines){
                    values.add(str)
                    str=""
                    for (k in j.elements) {
                        if (!tree.contains(k.text.toLowerCase().trim()))
                            str = "$str ${k.text.trim()}"
                    }
                    println(str)
                }

            }
        }
        values.add(str)//at end of receipt
        println(values)

        myAdapter = MyAdapter(values.toTypedArray())
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply{
            layoutManager = manager
            adapter = myAdapter
        }


        //need to implement editting for each row, after editting then need to push to DB
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


    //creates the dictionary of terms to remove
    private fun loadTree(): RBT<String>{
        //load the dictionary of terms to skip
        val tree = RBT<String>()
        try{
            val `is` = this.assets.open("dictionary.txt")//create a file input stream and create a red black tree for O(log[2](N)) search functionality
            `is`.bufferedReader().forEachLine {
                tree.insert(it) //each line of dictionary.txt is its own entry
            }
        }catch (e: IOException){
            Toast.makeText(this, "Failed to loaded data", Toast.LENGTH_SHORT).show()
        }
        Toast.makeText(this, "Successfully loaded data", Toast.LENGTH_SHORT).show()
        return tree
    }

    private fun loadKeyTree():RBT<String>?{
        //loads a dictionary of key terms to help catagorize the individual items
        val tree = RBT<String>()
        try{
            val `is` = this.assets.open("regexDict.txt")
            `is`.bufferedReader().forEachLine {
                tree.insert(it)
            }
        }catch (e: IOException){
            Toast.makeText(this, "Failed to loaded data", Toast.LENGTH_SHORT).show()
            return null
        }
        return tree
    }


       //parses and submits final list of items to DB
    //will want to convert all strings and items to all upper case or all lower case for ease of DB management
    private fun submitItems(){

    }


//parses out information that the user does not need or that we determine to be bad info, will probably capture items that we want to keep but will work overall
   private fun runDBParser(): ArrayList<String>{
       val regexlist = loadRegex()
       var ans = ArrayList<String>()
       for(x in values){ //for each line of text in the document get rid of bad terms
           for(y in regexlist){

           }
       }

    return ans
   }
    private fun loadRegex(): ArrayList<Regex>{
        var ans = ArrayList<Regex>()
        try{
            val `is` = this.assets.open("regexDict.txt")
            `is`.bufferedReader().forEachLine {
                ans.add(Regex.fromLiteral(it))
            }
        }catch (e: IOException){
            Toast.makeText(this, "Failed to loaded data", Toast.LENGTH_SHORT).show()
        }
        return ans
    }

}


class MyAdapter(private val myDataSet: Array<String>): RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    class ViewHolder(private val view: View):RecyclerView.ViewHolder(view){
    fun bind(text: String){
        val tv = view.findViewById<TextView>(R.id.textView)
        tv.text = text

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val vh = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(vh)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(myDataSet[position])
    }
    override fun getItemCount(): Int{
        return myDataSet.size
    }
    fun getDataSet(): Array<String>{
        return myDataSet
    }
}