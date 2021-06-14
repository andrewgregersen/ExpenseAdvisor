package com.example.loginimplenetation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect
import android.widget.Toast
import com.example.loginimplenetation.adapter.RegexHelper
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import java.io.IOException

//Mostly Written by Andrew Gregersen for Senior Capstone at Bellevue College

//AdapterView.OnItemSelectedListener for spinner
class TextRecognitionActivity {


    private fun runTextRecognition(mSelectedImage: Bitmap, context: Context) {
        val image = InputImage.fromBitmap(mSelectedImage, 0)
        val recognizer = TextRecognition.getClient()
        recognizer.process(image)
            .addOnSuccessListener { texts ->
                processTextRecognition(texts, context)
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed to read text", Toast.LENGTH_SHORT).show()
            }
    }


    private fun processTextRecognition(texts: Text, context: Context) {
        var blocks = texts.textBlocks
        if (blocks.size == 0) {
            Toast.makeText(context, "No text found", Toast.LENGTH_LONG).show()
            return
        }
        val tree = loadTree(context)
        var values: ArrayList<String> = arrayListOf()


        var str = "" //consider this to be a single item/line
        var prev: Rect? = null
        for (i in blocks) {
            //println(i.boundingBox)

            if (prev == null) {
                prev = i.boundingBox
                for (j in i.lines)
                    for (k in j.elements) {
                        str = "$str ${k.text.trim()}"
                    }
            } else if ((-4 < i.boundingBox?.top?.minus(prev.top)!!) && (10 > i.boundingBox?.top?.minus(
                    prev.top
                )!!)
            ) {
                for (j in i.lines) {//the blocks are on the same line within a small margin of error
                    if (prev != null) {
                        if (i.boundingBox?.left!! < prev.left!!) { //if current bounding box's left most border is at a x-loc smaller than the previous ones
                            var temp = ""
                            for (k in j.elements) {
                                if (!tree.contains(k.text.toLowerCase().trim()))
                                    temp =
                                        "$temp ${k.text.trim()}" //adds elements to string place 1: A left bounding box on a line was called after the one right of it
                            }
                            str = "$temp $str" //place the items before the current string
                        } else {//continue as normal
                            for (j in i.lines) {
                                for (k in j.elements) {
                                    if (!tree.contains(k.text.toLowerCase().trim()))
                                        str =
                                            "$str ${k.text.trim()}" //adds elements to the string place 2 : Adds to line in order (concatenation)
                                }
                            }
                        }
                    }
                    prev = i.boundingBox //save the previous bounding box
                }
            } else {//blocks are not on the same line

                prev = i.boundingBox
                for (j in i.lines) {
                    values.add(str) //adds previous line from receipt to Arraylist *should be one item
                    str = ""
                    for (k in j.elements) {
                        if (!tree.contains(k.text.toLowerCase().trim()))
                            str = "$str ${k.text.trim()}"
                    }
                    //println(str)
                }

            }
        }
        values.add(str)//at end of receipt
        //println(values)
        values = RegexHelper().runParserForUserDisplay(values)

        
//        myAdapter = MyAdapter(RegexHelper().runParserForUserDisplay(values).toTypedArray())
//        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
//            layoutManager = manager
//            adapter = myAdapter
//        }


//        //need to implement editting for each row, after editting then need to push to DB
//        submit = findViewById(R.id.Submit)
//        submit.setOnClickListener {
//
//            pushToDB((myAdapter as MyAdapter).getDataSet())


        /*
        var a = ArrayList<String>()
        for(x in recyclerView){
            var l = findViewById<EditText>(x.id)
            a.add(l.text.toString().trim())
        }
        pushToDB(a)
        Toast.makeText(this,"Receipt Added Successfully",Toast.LENGTH_SHORT).show()

         */
    }


//    //function that creates receipts and provides them to the DB
//    private fun pushToDB(tc: Array<String>) {
//        var tp = ArrayList<String>()
//        for (x in tc) {
//            tp.add(x)
//        }
//        var timeStamp = ""
//        var bool1 = false
//        var bool2 = false
//        var bool3 = false
//        var index1 = -1
//        var index2 = -1
//        var index3 = -1
//        var total = ""
//        var tax = ""
//
//        /*
//            Finds, stores and removes the timestamp, total and tax from the list
//         */
//        for (x in tp) {
//            if (Regex(pattern = "([^a-zA-Z#]+\\d+[:\\-\\/]\\d+)").containsMatchIn(x) && !bool1) {//look for the timestamp (should be in the first run, but in case its not)
//                timeStamp = x
//                index1 = tp.indexOf(x)//remove it from the list
//                bool1 = !bool1
//                continue
//            } else if (Regex(pattern = "(total.|Total.|TOTAL.)").containsMatchIn(x) && !bool2) { //look for the total (should be the second run, but in case its not)
//                val e = x.split(regex = Regex(pattern = "(total.|Total.|TOTAL.)"))
//                total = e[1].trim()
//                total = total.removePrefix("$")
//                index2 = tp.indexOf(x) //remove it from the List
//                bool2 = !bool2
//                continue
//            } else if (Regex(pattern = "(tax.|Tax.|TAX.)").containsMatchIn(x) && !bool3) { //look for the total (should be the third run, but in case its not)
//                val e = x.split(regex = Regex(pattern = "(tax.|Tax.|TAX.)"))
//                tax = e[1].trim()
//                tax = total.removePrefix("$")
//                index3 = tp.indexOf(x) //remove it from the List
//                bool2 = !bool2
//                continue
//            }
//            if (bool1 && bool2 && bool3)
//                break
//        }
//        //remove them from the list
//        tp.removeAt(index1)
//        tp.removeAt(index2)
//        tp.removeAt(index3)
//
//        val items = RegexHelper().parseforDB(tp)
//
//        //finally push to the database
//
//        //create a new receipt
//        val RID = DatabaseHelper(this).insertReceipt(
//            total.toDouble(),
//            "Test"
//        ) //create a new receipt at a Testing location, will need to be updated either dynamically or from user input
//
//
//        //insert the items into the DB in a new testing category will need to be dynamically updated based upon the items name
//        for (x in items.keys) {
//            DatabaseHelper(this).insertItem(
//                x,
//                items[x]?.keys!!.elementAt(0),
//                items[x]?.values!!.elementAt(0),
//                "Test"
//            )
//        }
//
//    }


    //creates the dictionary of terms to remove
    private fun loadTree(context: Context): RBT<String> {
        //load the dictionary of terms to skip
        val tree = RBT<String>()
        try {
            val `is` =
                context.assets.open("dictionary.txt")//create a file input stream and create a red black tree for O(log[2](N)) search functionality
            `is`.bufferedReader().forEachLine {
                tree.insert(it) //each line of dictionary.txt is its own entry
            }
        } catch (e: IOException) {
            Toast.makeText(context, "Failed to loaded data", Toast.LENGTH_SHORT).show()
        }
        //Toast.makeText(this, "Successfully loaded data", Toast.LENGTH_SHORT).show()
        return tree
    }

}