package com.example.loginimplenetation.adapter

class RegexHelper {



    //returns a string <Name, Quantity, Price>
    //unless it is a time stamp <Date, Time> or <Time, Date>

    fun runParserForUserDisplay(s: String):String{
        val a = ArrayList<Regex>()
        a.add(Regex.fromLiteral("([^\\d+])"))//removes words that are only numbers
        a.add(Regex.fromLiteral("([a-zA-Z\\s])"))//gets the name of an item
        a.add(Regex.fromLiteral("([^\\sa-zA-z]+)"))//selects the price from an item
        a.add(Regex.fromLiteral("([a-zA-Z\\s]+"))//lines that are only words
        a.add(Regex.fromLiteral("([^a-zA-Z\\s#]+\\d+[:\\-\\/]\\d"))//used to find the timestamp on a receipt
        var r = s.split(" ")
        var ans = ""
        for(x in r){
            if(a[0].containsMatchIn(x))
                continue
            else if(a[1].containsMatchIn(x))
                ans = "$ans $x" //parses out name
            else if(a[2].containsMatchIn(x)){
                ans = "$ans $x"
                return ans //got the price return back to main main activity
            }
            else if(a[3].containsMatchIn(x)){
                continue
            }
            else if(a[4].containsMatchIn(x))
                ans="$ans $x"
        }
        return ans
    }


    fun runParseForDB(s: String):ArrayList<String>{
        val a = ArrayList<Regex>()
        a.add(Regex.fromLiteral("([a-zA-Z\\s])"))//gets the name of an item
        a.add(Regex.fromLiteral("([^\\sa-zA-z]+)"))//selects the price from an item
        a.add(Regex.fromLiteral("([^a-zA-Z\\s#]+\\d+[:\\-\\/]\\d"))//used to find the timestamp on a receipt also the least common answer
        val r = s.split(" ")
        var ans = ArrayList<String>()
        for(x in r){
            if(a[0].containsMatchIn(x))
                ans.add(x) //parses out name
            else if(a[1].containsMatchIn(x)){
                ans.add(x)
                return ans //got the price return back to main main activity also at the end of a line
            }
            else if(a[2].containsMatchIn(x))
                ans.add(x)
        }
        return ans
        }



}