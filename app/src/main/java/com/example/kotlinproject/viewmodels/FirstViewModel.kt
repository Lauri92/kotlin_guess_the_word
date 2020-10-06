/*
Lauri Riikonen
1909911
 */
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


    //Gather the data from Json file in "https://users.metropolia.fi/~lauriari/" and create words and place them in repository
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
            Log.i("FirstViewModel","Repository all words: ${wordRepository.getWords().value}")
            Log.i("FirstViewModel","Repository random word: ${wordRepository.getRandomWord().value?.imgSrcUrl}")
            Log.i("FirstViewModel","Repository words for wrong answers: ${wordRepository.getDummyWords().value}")
        }
    }

    //Creation of words
    private fun createWord(lang: String, text: String, translateLang: String, translateText: String,
                           imgSrcUrl: String) {
        val wordToAdd = Word(lang, text)
        wordToAdd.addTranslation(Word(translateLang, translateText))
        wordToAdd.imgSrcUrl = imgSrcUrl
        wordRepository.addDummyWord(translateText)
        wordRepository.addWord(wordToAdd)
    }
}