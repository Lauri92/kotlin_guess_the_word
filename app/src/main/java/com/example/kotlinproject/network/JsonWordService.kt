package com.example.kotlinproject.network

import com.example.kotlinproject.JsonWord
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://users.metropolia.fi/~lauriari/"


/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


/**
 * A public interface that exposes the [getProperties] method
 */
interface JsonWordService {
    @GET("words.json")
    suspend fun getProperties(): List<JsonWord>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object JsonWordsApi {
    val retrofitService : JsonWordService by lazy {
        retrofit.create(JsonWordService::class.java)
    }
}
