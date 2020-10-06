/*
Lauri Riikonen
1909911
 */
package com.example.kotlinproject.badlearnedwords

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kotlinproject.Word


@Entity(tableName = "bad_learned_words_table")
data class BadLearnedWord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "bad_learned_word")
    val badLearnedWord: String
)