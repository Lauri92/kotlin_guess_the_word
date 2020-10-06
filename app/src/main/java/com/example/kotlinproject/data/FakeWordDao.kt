/*
Lauri Riikonen
1909911
 */
package com.example.kotlinproject.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinproject.Word

class FakeWordDao {
    //A fake database table
    private val wordList = mutableListOf<Word>()
    private val dummyWordList = mutableListOf<String>()

    //Words to display as wrong answers
    private val dummyWords = MutableLiveData<List<String>>()
    private val words = MutableLiveData<List<Word>>()
    private var word = MutableLiveData<Word>()


    init {
        Log.i("FakeWordDao", "word value: ${word.value}")
    }


    fun addWord(word: Word) {
        wordList.add(word)
        words.value = wordList
    }

    fun getWords() = words as LiveData<List<Word>>

    fun getRandomWord() : LiveData<Word> {
        word.value = wordList.random()
        return word
    }

    fun addDummyWord(dummyWord: String) {
        dummyWordList.add(dummyWord)
        dummyWords.value = dummyWordList
    }

    fun getDummyWords() = dummyWords as LiveData<List<String>>

}