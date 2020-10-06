/*
Lauri Riikonen
1909911
 */
package com.example.kotlinproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


data class Word(val lang: String, val text: String) {

    lateinit var imgSrcUrl : String

    val translations = mutableSetOf<Word>()

    fun addTranslation(t: Word) {
        translations.add(t)
    }

    fun addTranslations(ts: Set<Word>) {
        translations.addAll(ts)
    }

    fun isTranslation(word: Word): Boolean {
        return translations.any { it.lang == word.lang && it.text == word.text }
    }

    fun translationCount(lang: String): Int {
        return translations.count { it.lang == lang }
    }

    fun closestTranslations(word: Word): List<Word> {
        return translations
            .filter { it.lang == word.lang }
            .map { Pair(it, it.editDistance(word)) }
            .sortedBy { it.second }
            .map { it.first }
    }

    // Levenshtein distance with Wagner-Fischer algorithm
    // See https://en.wikipedia.org/wiki/Wagner%E2%80%93Fischer_algorithm
    fun editDistance(another: Word): Int {
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

object All {
    val words = mutableSetOf<Word>()
}