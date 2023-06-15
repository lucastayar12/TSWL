package com.example.tswl.repository

object IndexSingleton {

    var index : Int = 0

    fun incrementIndex(){
        index++
    }

    fun resetIndex(){
        index = 0
    }
}