package com.example.kotlinproject

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//@Parcelize
data class JsonWord(val id: String,
                    val lang: String,
                    val text: String,
                    val translateLang: String,
                    val translateText: String,
                    val imgSrcUrl: String)

    /*: Parcelable*/ {
    private val translations = mutableSetOf<JsonWord>()

    fun addTranslation(t: JsonWord) {
        translations.add(t)
    }

    fun addTranslations(ts: Set<JsonWord>) {
        translations.addAll(ts)
    }

    fun isTranslation(word: JsonWord): Boolean {
        return translations.any { it.lang == word.lang && it.text == word.text }
    }

    fun translationCount(lang: String): Int {
        return translations.count { it.lang == lang }
    }

    fun closestTranslations(word: JsonWord): List<JsonWord> {
        return translations
            .filter { it.lang == word.lang }
            .map { Pair(it, it.editDistance(word)) }
            .sortedBy { it.second }
            .map { it.first }
    }

    // Levenshtein distance with Wagner-Fischer algorithm
    // See https://en.wikipedia.org/wiki/Wagner%E2%80%93Fischer_algorithm
    fun editDistance(another: JsonWord): Int {
        val m = this.text.length
        val n = another.text.length

        val d: Array<IntArray> = Array(m+1) { IntArray(n+1) {0} } // set all (m+1) * (n+1) elements to zero

        for(i in 0 until m+1) {
            d[i][0] = i
        }

        for(j in 0 until n+1) {
            d[0][j] = j
        }

        for(j in 0 until n) {
            for(i in 0 until m) {
                val cost = if(text[i] == another.text[j]) 0 else 1
                d[i+1][j+1] = minOf(d[i][j+1] + 1, d[i+1][j] + 1, d[i][j] + cost)
            }
        }

        return d[m][n]
    }
}

object AllJsonWord {
    val words = mutableSetOf<JsonWord>()
}