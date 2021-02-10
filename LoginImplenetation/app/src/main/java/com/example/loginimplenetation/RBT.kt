package com.example.loginimplenetation



/*
Please look at the code here: https://codereview.stackexchange.com/questions/177924/red-black-tree-implementation-in-kotlin
This is what this code is based off of, albeit stripped down to only what I need to use in the project
-Andrew Gregersen
 */

class RBT <T: Comparable<Any>> {
    private var size: Int = 0
    private lateinit var overRoot: Node<T>
    //default constrictor for tree
    fun RBT(){
        overRoot = Node(null,null,null,null,false)
    }

    //initialize the RBT with some value
    fun RBT(key: T, left: Node<T>, right: Node<T>){
        overRoot = Node(key,left,right,null,false)
    }
 
    fun getSize(): Int{
        return size
    }


    fun get(key:T): T? {
        var temp = overRoot
        while(temp!=null){
            val cmp = key.compareTo(temp.key)
            when{
                cmp<0 -> temp = temp.left!! //will not be null regardless
                cmp>0 -> temp = temp.right!!
                else -> return temp.key
            }
        }
        return null
    }

    private fun isRed(node: Node<T>): Boolean{
        return node.red
    }

    private fun rotateLeft(h: Node<T>): Node<T>{
        assert(isRed(h))
        val x = h.right
        h.right = x?.left
        x?.left = h
        val left = x?.left
        x?.red = left!!.red
        return x
    }

    private fun rotateRight(h: Node<T>): Node<T>{
        assert(isRed(h))
        val x = h.left
        h.left = x?.right
        x?.right = h
        val right = x?.right
        x?.red = right!!.red
        return x
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
    class Node<T: Comparable<Any>>(
        key: Any?,
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