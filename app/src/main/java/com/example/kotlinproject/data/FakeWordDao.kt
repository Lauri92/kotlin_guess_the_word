package com.example.kotlinproject.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinproject.Word

class FakeWordDao {
    //A fake database table
    private val wordList = mutableListOf<Word>()
    private val dummyWordList = mutableListOf<String>()

    //MutableLiveData is from the Architecture Components Library
    //LiveData can be observed for changes
    private val dummyWords = MutableLiveData<List<String>>()
    private val words = MutableLiveData<List<Word>>()
    private var word = MutableLiveData<Word>()


    init {/*
        val w = Word("Finnish", "kaivo")
        w.addTranslation(Word("English", "well"))
        val w2 = Word("Finnish", "seinä")
        w2.addTranslation(Word("English", "wall"))
        val w3 = Word("Finnish", "auto")
        w3.addTranslation(Word("English", "car"))
        val w4 = Word("Finnish", "kissa")
        w4.addTranslation(Word("English", "cat"))
        val w5 = Word("Finnish", "koira")
        w5.addTranslation(Word("English", "dog"))
        w5.addTranslation(Word("English", "hound"))
        addWord(w)
        addWord(w2)
        addWord(w3)
        addWord(w4)
        addWord(w5)
*/

        //words.value = wordList
        //word.value = wordList.random()
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