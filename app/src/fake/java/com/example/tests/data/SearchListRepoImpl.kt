package com.example.tests.data

import com.example.tests.data.retrofit.SearchListApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.dk.mydictionary.data.SearchListRepo
import ru.dk.mydictionary.data.model.DictionaryModel
import ru.dk.mydictionary.data.model.Meaning
import ru.dk.mydictionary.data.model.Translation

class SearchListRepoImpl(private val api: SearchListApi) : SearchListRepo {

    private val list = mutableListOf<DictionaryModel>()


    private fun getFakeList(): List<DictionaryModel> {
        for (i in 1..20) {
            list.add(
                DictionaryModel(
                    listOf(Meaning("", "", "", Translation("тест $i"))),
                    "test $i"
                )
            )
        }
        return list
    }

    override suspend fun getData(word: String): Flow<List<DictionaryModel>>? = flowOf(getFakeList())
}