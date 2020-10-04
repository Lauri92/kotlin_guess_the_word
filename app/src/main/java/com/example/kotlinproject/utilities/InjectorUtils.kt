package com.example.kotlinproject.utilities

import com.example.kotlinproject.data.FakeDatabase
import com.example.kotlinproject.data.WordRepository
import com.example.kotlinproject.factories.FirstViewModelFactory
import com.example.kotlinproject.factories.SecondViewModelFactory
import com.example.kotlinproject.factories.ThirdViewModelFactory

object InjectorUtils {

    fun provideFirstViewModel(): FirstViewModelFactory {
        val wordRepository = WordRepository.getInstance(FakeDatabase.getInstance().wordDao)
        return FirstViewModelFactory(wordRepository)
    }

    fun provideSecondViewModel(): SecondViewModelFactory {
        val wordRepository = WordRepository.getInstance(FakeDatabase.getInstance().wordDao)
        return SecondViewModelFactory(wordRepository)
    }

    fun provideThirdViewModel(): ThirdViewModelFactory {
        val wordRepository = WordRepository.getInstance(FakeDatabase.getInstance().wordDao)
        return ThirdViewModelFactory(wordRepository)
    }
}