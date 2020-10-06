/*
Lauri Riikonen
1909911
 */package com.example.kotlinproject.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.kotlinproject.Word
import com.example.kotlinproject.badlearnedwords.BadLearnedWordRepository
import com.example.kotlinproject.data.FakeWordDao
import com.example.kotlinproject.data.WordRepository
import com.example.kotlinproject.network.JsonWordsApi
import kotlinx.coroutines.launch
import java.lang.Exception


class SecondViewModel(private val wordRepository: WordRepository,
                      private val badLearnedWordRepository: BadLearnedWordRepository,
                      application: Application) : AndroidViewModel(application) {

    private fun getWords() = wordRepository.getWords()

    private fun addWord(word: Word) = wordRepository.addWord(word)

    private fun getRandomWord() = wordRepository.getRandomWord()

    // The current score
    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    //The current displayedWord, the word which user has to translate
    private var _displayedWord = MutableLiveData<String>()
    val displayedWord: LiveData<String>
        get() = _displayedWord

    //Word object
    private var _word = MutableLiveData<Word>()
    val word: LiveData<Word>
        get() = _word

    //Check user input
    private var _buttonPressed = MutableLiveData<Boolean>()
    val buttonPressed: LiveData<Boolean>
        get() = _buttonPressed


    private fun viewModelButtonpressedCheck() {
        _buttonPressed.value = true
    }

    fun viewModelButtonpressedCheckDone() {
        _buttonPressed.value = false
    }


    init {
        Log.i("SecondViewModel", "SecondViewModel Created!")
        _score.value = 0
        selectWord()
    }


    fun selectWord() {
        _word = getRandomWord() as MutableLiveData<Word>
        _displayedWord.value = word.value?.text
        Log.i("SecondViewModel", "value: ${word.value}\n" +
                "translationsCount: ${word.value?.translationCount("English")}\n")
    }

    fun incrementScore() {
        _score.value = score.value?.plus(1)
    }

    fun decrementScore() {
        _score.value = score.value?.minus(1)
    }


    fun checkAnswer() {
        viewModelButtonpressedCheck()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SecondViewModel", "SecondViewModel destroyed!")
    }

}