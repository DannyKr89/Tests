package com.example.tests

import android.app.Application
import com.example.tests.data.SearchListRepoImpl
import com.example.tests.data.retrofit.SearchListApi
import com.example.tests.ui.viewmodels.SearchListViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dk.mydictionary.data.SearchListRepo
import tech.thdev.network.flowcalladapterfactory.FlowCallAdapterFactory

class App : Application() {

    private val retrofit = Retrofit.Builder().baseUrl("https://dictionary.skyeng.ru/")
        .addCallAdapterFactory(FlowCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val api = retrofit.create(SearchListApi::class.java)

    private val repository: SearchListRepo by lazy {
        SearchListRepoImpl(api)
    }

    val viewModel = SearchListViewModel(repository)
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        internal lateinit var instance: App
            private set
    }

}