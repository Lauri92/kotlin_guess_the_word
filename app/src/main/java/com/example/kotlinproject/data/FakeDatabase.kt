/*
Lauri Riikonen
1909911
 */
package com.example.kotlinproject.data

class FakeDatabase private constructor(){

    var wordDao = FakeWordDao()
        private set

    companion object {
        @Volatile private var instance: FakeDatabase? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: FakeDatabase().also { instance = it}
            }
    }
}