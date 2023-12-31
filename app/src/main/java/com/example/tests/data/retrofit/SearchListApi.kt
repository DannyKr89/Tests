package ru.dk.mydictionary.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.dk.mydictionary.data.model.DictionaryModel

interface SearchListApi {
    @GET("api/public/v1/words/search")
    fun getList(
        @Query("search") word: String,
        @Query("pageSize") pageSize: Int = 100
    ): Call<List<DictionaryModel>>?
}