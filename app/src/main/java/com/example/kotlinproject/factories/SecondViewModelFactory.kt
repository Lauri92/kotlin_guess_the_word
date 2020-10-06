/*
Lauri Riikonen
1909911
 */
package com.example.kotlinproject.factories

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinproject.badlearnedwords.BadLearnedWordDao
import com.example.kotlinproject.badlearnedwords.BadLearnedWordRepository
import com.example.kotlinproject.data.WordRepository
import com.example.kotlinproject.viewmodels.SecondViewModel

//Access to real database too, but it has no usage
class SecondViewModelFactory(private val wordRepository: WordRepository,
                             private val badLearnedWordRepository: BadLearnedWordRepository,
                             private val application: Application
) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        Log.i("SecondViewModelFactory", "SecondViewModelFactory visited")
        return SecondViewModel(wordRepository, badLearnedWordRepository, application) as T
    }
}