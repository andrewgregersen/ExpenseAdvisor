package com.example.loginimplenetation

class RBT <T: Any> {
    private lateinit var overRoot: Node<T>
    fun RBT(){
        overRoot = Node()
        overRoot.data = null
        overRoot.left = null
        overRoot.right = null
        overRoot.red = false
        overRoot.parent = null
    }

    //inserts an object of type T into the RBT
    fun insert(k: T){
        if(overRoot == null){
            overRoot.data = k
            return
        }
        insert
    }






    //node object to implement a RBT
    class Node<T:Any>{
        var data: T? = null
        var left: Node<T>? = null
        var right: Node<T>? = null
        var parent: Node<T>? = null
        var red: Boolean = false
    }
}