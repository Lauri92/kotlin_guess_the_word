/*
Lauri Riikonen
1909911
 */
package com.example.kotlinproject.badlearnedwords

import androidx.lifecycle.LiveData
import com.example.kotlinproject.badlearnedwords.BadLearnedWordDao
import com.example.kotlinproject.badlearnedwords.BadLearnedWord
import com.example.kotlinproject.data.FakeWordDao
import com.example.kotlinproject.data.WordRepository


class BadLearnedWordRepository(private val badLearnedWordDao: BadLearnedWordDao) {

    //val readAllData: LiveData<List<BadLearnedWord>> = badLearnedWordDao.readAllData()

    //Coroutines in viewmodel
    suspend fun addBadLearnedWord(badLearnedWordDao: BadLearnedWord) {
       // badLearnedWordDao.addBadLearnedWord(badLearnedWordDao)
    }

    suspend fun updateBadLearnedWord(badLearnedWord: BadLearnedWord) {
       // badLearnedWordDao.updateUser(badLearnedWord)
    }

    suspend fun deleteBadLearnedWord(badLearnedWord: BadLearnedWord) {
       // badLearnedWordDao.deleteUser(badLearnedWordDao)
    }

    suspend fun deleteAllBadLearnedWords(){
        //badLearnedWordDao.deleteAllUsers()
    }
}