package com.example.kotlinproject.factories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinproject.data.WordRepository
import com.example.kotlinproject.viewmodels.FirstViewModel

class FirstViewModelFactory(private val wordRepository: WordRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        Log.i("FirstViewModelFactory", "FirstViewModelFactory visited")
        return FirstViewModel(wordRepository) as T
    }
}