package com.example.kotlinproject.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.Word
import com.example.kotlinproject.data.FakeWordDao
import com.example.kotlinproject.data.WordRepository
import com.example.kotlinproject.network.JsonWordsApi
import kotlinx.coroutines.launch
import java.lang.Exception


class SecondViewModel(private val wordRepository: WordRepository) : ViewModel() {

    private fun getWords() = wordRepository.getWords()

    private fun addWord(word: Word) = wordRepository.addWord(word)

    private fun getRandomWord() = wordRepository.getRandomWord()

    // The current score
    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    //The current displayedWord
    private var _displayedWord = MutableLiveData<String>()
    val displayedWord: LiveData<String>
        get() = _displayedWord

    //Word object
    private var _word = MutableLiveData<Word>()
    val word: LiveData<Word>
        get() = _word

    //The current answer
    private var _answer = MutableLiveData<String>()
    val answer: LiveData<String>
        get() = _answer


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
        //getJsonWordProperties()
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


/*
    private fun nextWord() {
        //Shuffle the word list, if the list is empty
        if (pairList.isEmpty()) {
            pairList()
        } else {
            //Select and remove a word from the list
            _word.value = pairList.removeAt(0)
        }
    }

 */

    fun checkAnswer() {
        viewModelButtonpressedCheck()
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }

    private fun createWord(lang: String, text: String, translateLang: String, translateText: String ) {
        val lisattava = Word(lang, text)
        lisattava.addTranslation(Word(translateLang, translateText))
        wordRepository.addWord(lisattava)
    }

    private fun getJsonWordProperties() {
        viewModelScope.launch {
            try {
                val properties = JsonWordsApi.retrofitService.getProperties()
                Log.i("SecondViewModel", "List: $properties")
                properties.forEach {
                    Log.i("SecondViewModel", it.id + "\n")
                    createWord(it.translateLang, it.translateText, it.lang, it.text)
                }
            } catch (e: Exception) {
                Log.i("SecondViewModel","Error: $e")
            }
        }
    }

}