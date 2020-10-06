/*
Lauri Riikonen
1909911
 */
package com.example.kotlinproject.badlearnedwords

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BadLearnedWord::class], version = 1, exportSchema = false)
abstract class BadLearnedWordDatabase : RoomDatabase() {

    abstract val badLearnedWordDao: BadLearnedWordDao

    companion object {
        @Volatile
        private var INSTANCE: BadLearnedWordDatabase? = null

        fun getInstance(context: Context): BadLearnedWordDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BadLearnedWordDatabase::class.java,
                        "bad_learned_word_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}