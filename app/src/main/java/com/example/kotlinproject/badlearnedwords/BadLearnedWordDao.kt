/*
Lauri Riikonen
1909911
 */
package com.example.kotlinproject.badlearnedwords

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BadLearnedWordDao {

    @Insert
    suspend fun addBadLearnedWord(badLearnedWord: BadLearnedWord)

    @Update
    suspend fun updateBadLearnedWord(badLearnedWord: BadLearnedWord)

    @Query("DELETE FROM bad_learned_words_table")
    suspend fun deleteAllBadLearnedWords()

    @Query("SELECT * FROM bad_learned_words_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<BadLearnedWord>>
}