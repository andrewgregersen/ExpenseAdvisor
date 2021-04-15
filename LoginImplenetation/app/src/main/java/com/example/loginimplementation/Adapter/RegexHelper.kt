package com.example.loginimplementation.Adapter

import kotlin.text.Regex

class RegexHelper {



    //returns a string <Name, Quantity, Price>
    //unless it is a time stamp <Date, Time> or <Time, Date>

    fun runParserForUserDisplay(s: ArrayList<String>):ArrayList<String>{
        var ans1= ArrayList<String>()
        var found = false
        var temp = ""
         //gets timestamp
        for(x in s) { //for each line in the receipt
            if(Regex(pattern = "([^a-zA-Z#]+\\d+[:\\-\\/]\\d+)").containsMatchIn(x) && !found) {
                found = true
                ans1.add(0,storeTimeStamp(x)) //any potential timestamp that the receipt might have, runs only once if at all
                continue
            }
            if(Regex(pattern = "(total.|Total.|TOTAL.)").containsMatchIn(x)){
                ans1.add(1,x) //let the user deal with extra garbage words
                continue
            }
            val y = x.trim().split(" ")
            temp = ""
            loop@ for (z in y) { //go word by word
                when{
                    Regex(pattern = "(\\d{4,})").matches(z) -> continue@loop //skips elements that are numbers and have more than four digits
                    Regex(pattern = "(\\d{1,}\\W\\s{0,}[^\\d])").matches(z)-> continue@loop
                    Regex(pattern = "([a-zA-Z]+)").matches(z) -> temp = "$temp $z" //gets all strings
                    Regex(pattern = "([^a-zA-z]+)").matches(z)-> temp = "$temp $z"//gets all prices
                }
            }
            if(temp != ""){
                ans1.add(temp.trim())
            }
        }
        return ans1
    }



    fun storeTimeStamp(s: String):String{
        var ans: String = ""
        val y = s.split(" ")
        for(x in y){
            x.trim()
            when{
                Regex(pattern = "([^a-zA-Z#]+\\d+[:\\-\\/]\\d+)").matches(x) -> ans = "$ans $x"
            }

        }
        return ans
    }

    fun parseforDB(s:ArrayList<String>):Map<String,Map<Double,Int>>{
        var itemList =HashMap<String,Map<Double,Int>>()
        //prepare items in the display list to be pushed to the DB
        val b = HashMap<Double, Int>()
        var name = ""
        var amount = 0
        var price = 0.0
        for(y in s){
            val a = y.split(" ")
            name = ""
            amount = 0
            price = 0.0
            for(x in a){
                when{
                    Regex(pattern = "([a-zA-Z]+)").matches(x) -> name = "$name $x"
                    Regex(pattern = "(\\d{1,3})").matches(x) -> amount = x.toInt() //gets the amount of items requested
                    Regex(pattern = "([^a-zA-z]+)").matches(x)-> {
                        val y = x.split(regex = Regex(pattern = "([$])"))
                        if(y.isEmpty())
                            price = x.trim().toDouble()
                        else if (y.size==1)
                            price= y[0].trim().toDouble()
                        else
                            price= y[1].trim().toDouble()

                    }
                }
            }
            b.clear()
            b[price] = amount
            itemList[name] = b
        }

        return itemList
    }




}