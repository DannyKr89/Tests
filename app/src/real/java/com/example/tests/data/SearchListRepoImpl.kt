package com.example.tests.data

import com.example.tests.data.retrofit.SearchListApi
import kotlinx.coroutines.flow.Flow
import ru.dk.mydictionary.data.SearchListRepo
import ru.dk.mydictionary.data.model.DictionaryModel

class SearchListRepoImpl(private val api: SearchListApi) : SearchListRepo {

    override suspend fun getData(word: String): Flow<List<DictionaryModel>>? = api.getList(word)
}