package com.example.kotlinproject.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinproject.JsonWord
import com.example.kotlinproject.Word
import com.example.kotlinproject.data.WordRepository

class ThirdViewModel(private val wordRepository: WordRepository) : ViewModel() {


    data class Quiz(
        val correctAnswer: Word?,
        val allAnswers: List<String>
    )

    //lateinit var currentQuiz: Quiz

    private var _currentQuiz = MutableLiveData<Quiz>()
    val currentQuiz: LiveData<Quiz>
        get() = _currentQuiz

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
    // with new values
    private val _correctWordProperties = MutableLiveData<Word>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val correctWordProperties: LiveData<Word>
        get() = _correctWordProperties


    private val _wrongAnswers = MutableLiveData<List<String>>()
    val wrongAnswers: LiveData<List<String>>
        get() = _wrongAnswers


    private val _allWords = MutableLiveData<List<Word>>()
    val allWords: LiveData<List<Word>>
        get() = _allWords

    init {
        _allWords.value = wordRepository.getWords().value
        createNewQuiz()
        //Log.i("ThirdViewModel", "Image Url: ${_correctWordProperties.value?.imgSrcUrl}")
        //Log.i("ThirdViewModel", "Wrong answers value: ${_wrongAnswers.value}")
    }

    fun getNewPicture() {
        //_correctWordProperties.value = wordRepository.getRandomWord().value
        createNewQuiz()
    }

    fun createNewQuiz() {

        do {
            _correctWordProperties.value = wordRepository.getRandomWord().value
        } while (_correctWordProperties.value?.imgSrcUrl == "")


        //Choose a random correct translation
        val testString: String = _correctWordProperties.value?.translations!!.random().text


        val list = mutableListOf<String>()
        list.add(testString)
        var i = 0

        while (i < 3) {
            val toAdd = _allWords.value?.random()
            if ((toAdd != _correctWordProperties.value) && !list.contains(toAdd?.translations?.random()?.text)) {
                list.add(toAdd!!.translations.random().text)
                i++
            }
            _wrongAnswers.value = list
        }


        list.shuffle()
        _currentQuiz = MutableLiveData(Quiz(_correctWordProperties.value, list))
        Log.i(
            "ThirdViewModel",
            "newQuiz correct: ${_currentQuiz.value}"
        )

        Log.i("ThirdViewModel", "list translations: ${_currentQuiz.value?.allAnswers}")

        Log.i("ThirdViewModel", "testword: $testString")

        Log.i("ThirdViewModel", "_wrongAnswers: ${wrongAnswers.value}")

    }


}