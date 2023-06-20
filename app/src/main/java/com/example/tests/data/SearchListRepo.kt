package ru.dk.mydictionary.data

import retrofit2.Call
import ru.dk.mydictionary.data.model.DictionaryModel

interface SearchListRepo {
    fun getData(word: String): Call<List<DictionaryModel>>?
}