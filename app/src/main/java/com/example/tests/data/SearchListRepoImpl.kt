package ru.dk.mydictionary.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.dk.mydictionary.data.model.DictionaryModel
import ru.dk.mydictionary.data.retrofit.SearchListApi

class SearchListRepoImpl : SearchListRepo {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dictionary.skyeng.ru/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api = retrofit.create(SearchListApi::class.java)

    override fun getData(word: String): Call<List<DictionaryModel>>? = api.getList(word)
}