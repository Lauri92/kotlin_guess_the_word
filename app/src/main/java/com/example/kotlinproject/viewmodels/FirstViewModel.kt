package com.example.kotlinproject.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.Word
import com.example.kotlinproject.data.WordRepository
import com.example.kotlinproject.network.JsonWordsApi
import kotlinx.coroutines.launch
import java.lang.Exception

class FirstViewModel(private val wordRepository: WordRepository) : ViewModel() {

    init {
        getJsonWordProperties()
    }

    private fun createWord(lang: String, text: String, translateLang: String, translateText: String,
                           imgSrcUrl: String) {
        val wordToAdd = Word(lang, text)
        wordToAdd.addTranslation(Word(translateLang, translateText))
        wordToAdd.imgSrcUrl = imgSrcUrl
        wordRepository.addDummyWord(translateText)
        wordRepository.addWord(wordToAdd)
        //Log.i("FirstViewModel", "${wordToAdd.translations}")
    }

    private fun getJsonWordProperties() {
        viewModelScope.launch {
            try {
                val properties = JsonWordsApi.retrofitService.getProperties()
                Log.i("SecondViewModel", "List: $properties")
                properties.forEach {
                    //Log.i("FirstViewModel", it.id + "\n")
                    createWord(it.translateLang, it.translateText, it.lang, it.text, it.imgSrcUrl)
                }
            } catch (e: Exception) {
                Log.i("FirstViewModel","Error: $e")
            }
            Log.i("FirstViewModel","Repository : ${wordRepository.getWords().value}")
            Log.i("FirstViewModel","Repository : ${wordRepository.getRandomWord().value?.imgSrcUrl}")
            Log.i("FirstViewModel","Repository : ${wordRepository.getDummyWords().value}")
        }
    }
}