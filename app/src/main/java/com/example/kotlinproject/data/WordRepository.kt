package com.example.kotlinproject.data

import com.example.kotlinproject.Word

class WordRepository private constructor(private val wordDao: FakeWordDao) {

    //This may seem redundant
    //Imagine a code which also updates and checks the backend.
    fun addWord(word: Word) {
        wordDao.addWord(word)
    }

    fun getWords() = wordDao.getWords()

    fun addDummyWord(dummyWord: String) {
        wordDao.addDummyWord(dummyWord)
    }

    fun getDummyWords() = wordDao.getDummyWords()

    fun getRandomWord() = wordDao.getRandomWord()

    companion object {
        //Singleton instantiation you already know and love
        @Volatile private var instance: WordRepository? = null

        fun getInstance(wordDao: FakeWordDao) =
            instance ?: synchronized(this) {
                instance ?: WordRepository(wordDao).also { instance = it }
            }
    }
}