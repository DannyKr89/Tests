package com.example.tests.data

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.dk.mydictionary.data.SearchListRepo
import ru.dk.mydictionary.data.model.DictionaryModel
import ru.dk.mydictionary.data.model.Meaning
import ru.dk.mydictionary.data.model.Translation

class SearchListRepoImpl : SearchListRepo {

    private val list = mutableListOf<DictionaryModel>()

    val response: Response<List<DictionaryModel>> = Response.success(
        getFakeList()
    )

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


    override fun getData(word: String): Call<List<DictionaryModel>> {
        return object : Call<List<DictionaryModel>> {
            override fun clone(): Call<List<DictionaryModel>> {
                return this
            }

            override fun execute(): Response<List<DictionaryModel>> {
                return response
            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun cancel() {
            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun request(): Request {
                return Request.Builder().build()
            }

            override fun timeout(): Timeout {
                return Timeout()
            }

            override fun enqueue(callback: Callback<List<DictionaryModel>>) {
                callback.onResponse(this, response)
            }
        }
    }
}