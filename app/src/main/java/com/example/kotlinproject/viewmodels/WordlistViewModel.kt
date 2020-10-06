/*
Lauri Riikonen
1909911
 */
package com.example.kotlinproject.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinproject.JsonWord
import com.example.kotlinproject.network.JsonWordsApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


enum class JsonWordApiStatus {LOADING, ERROR, DONE}

/**
 * The [ViewModel] that is attached to the [WordlistFragment].
 */
class WordlistViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<JsonWordApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<JsonWordApiStatus>
        get() = _status


    // Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
    // with new values
    private val _properties = MutableLiveData<List<JsonWord>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val properties: LiveData<List<JsonWord>>
        get() = _properties

    /**
     * Call getJsonWordProperties() on init so we can display status immediately.
     */
    init {
        getJsonWordProperties()
    }

    /**
     * Gets JsonWord property information from the JsonWord API Retrofit service and updates the
     * [JsonWord] [List] [LiveData]. The Retrofit service returns a coroutine Deferred, which we
     * await to get the result of the transaction.
     */
    private fun getJsonWordProperties() {
        viewModelScope.launch {
            _status.value = JsonWordApiStatus.LOADING
            try {
                _properties.value = JsonWordsApi.retrofitService.getProperties()
                Log.i("WordlistViewModel", "List: ${properties.value}")
                _status.value = JsonWordApiStatus.DONE
            } catch (e: Exception) {
                _status.value = JsonWordApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }
}