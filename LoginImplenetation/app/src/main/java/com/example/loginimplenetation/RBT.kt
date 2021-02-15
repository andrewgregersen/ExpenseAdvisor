package com.example.loginimplenetation



/*
Please look at the code here: https://codereview.stackexchange.com/questions/177924/red-black-tree-implementation-in-kotlin
This is what this code is based off of, albeit stripped down to only what I need to use in the project
-Andrew Gregersen
 */

class RBT <T: Comparable<T>> {
    private var size: Int = 0
    private var overRoot: Node<T>?
    //default constrictor for tree
    constructor(){
        overRoot = null
    }

    //initialize the RBT with some value
    fun RBT(key: T, left: Node<T>? = null, right: Node<T>? = null){
        overRoot = Node(key,left,right,false)
    }

    fun getSize(): Int{
        return size
    }


    fun get(key:T): T? {
        var temp: Node<T>? = overRoot
        while(temp!=null){
            val cmp = temp.key?.let { key.compareTo(it) }
            if (cmp != null) {
                temp = when{
                    cmp<0 -> temp.left
                    cmp>0 -> temp.right
                    else -> return temp.key
                }
            }
        }
        return null
    }

    fun contains(key: T): Boolean{
        return get(key)?.compareTo(key)  ==0
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
        size++
        if(overRoot == null){
            overRoot = Node(k,null,null,false)
        }
        else if(k.compareTo(overRoot?.key!!)<0){
            overRoot?.left = insert(overRoot?.left,k)
        }
        else if(k.compareTo(overRoot?.key!!)>0){
            overRoot?.right = insert(overRoot?.right,k)
        }

    }

    private fun insert(root: Node<T>?, k: T): Node<T>{
        if(root == null){
            return Node(k,null,null,false)
        }
        val cmp = root.key?.let { k.compareTo(it) }
        if (cmp != null) {
            when{
                cmp <0 -> root.left = insert(root.left,k)
                cmp >0 -> root.left = insert(root.right,k)
                else -> return root //item is already in the tree
            }
        }
        if(root.right?.red!! && !root.left?.red!!)
            return rotateLeft(root)
        if(!root.right?.red!! && root.left?.red!!)
            return rotateRight(root)
        else
            return root
    }


/*
Dont need to implement delete, as I only need to read from this tree. If I need to change the contents of the tree, I will just change whats in the dictionary document
 */



    //node object to implement a RBT
    class Node<T: Comparable<T>> constructor(
        var key: T?,
        var left: Node<T>?,
        var right: Node<T>?,
        var red: Boolean = false
    )
}