package com.example.tests.data.retrofit

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query
import ru.dk.mydictionary.data.model.DictionaryModel

interface SearchListApi {
    @GET("api/public/v1/words/search")
    fun getList(
        @Query("search") word: String,
        @Query("pageSize") pageSize: Int = 100
    ): Flow<List<DictionaryModel>>?
}