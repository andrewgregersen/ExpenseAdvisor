package com.example.loginimplenetation



/*
Please look at the code here: https://codereview.stackexchange.com/questions/177924/red-black-tree-implementation-in-kotlin
This is what this code is based off of, albeit stripped down to only what I need to use in the project
-Andrew Gregersen
 */

class RBT <T: Comparable<T>,Value> {
    private lateinit var overRoot: Node<T>
    //default constrictor for tree
    fun RBT(){
        overRoot = Node(null,null,null,null,false)
    }

    //initialize the RBT with some value
    fun RBT(key: T, left: Node<T>, right: Node<T>){
        overRoot = Node(key,left,right,null,false)
    }


    fun get(key:T): T? {
        var temp = overRoot
        while(temp!=null){
            val cmp = key.compareTo(temp.key)
            when{
                cmp<0 -> temp = temp.left
                cmp>0 -> temp = temp.right
                else -> return temp.key
            }
        }
        return null
    }

    private fun isRed(node: Node<T>): Boolean{
        return node.red
    }

    //inserts an object of type T into the RBT
    fun insert(k: T){
        if(overRoot == null){
            overRoot.key = k
            return
        }
        else if(k.compareTo(overRoot.key)<0){
            insert(overRoot.left,k)
        }
        else if(k.compareTo(overRoot.key)<0){
            insert(overRoot.right,k)
        }
    }

    private fun insert(root: Node<T>?, k: T){
        if(root == null){

        }
    }






    //node object to implement a RBT
    class Node<T: Comparable<T>>(
        key: T,
        left: Node<T>?,
        right: Node<T>?,
        parent: Node<T>?,
        red: Boolean
    ) {
        lateinit var key: T
        var left: Node<T>? = null
        var right: Node<T>? = null
        var parent: Node<T>? = null
        var red: Boolean = false

    }
}