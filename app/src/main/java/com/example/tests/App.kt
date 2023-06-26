package com.example.tests

import android.app.Application
import com.example.tests.data.SearchListRepoImpl
import com.example.tests.presenters.SearchListPresenterImpl
import ru.dk.mydictionary.data.SearchListRepo
import ru.dk.mydictionary.presenters.SearchListPresenter

class App : Application() {

    private val repository: SearchListRepo by lazy {
        SearchListRepoImpl()
    }

    val presenter: SearchListPresenter by lazy {
        SearchListPresenterImpl(repository)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        internal lateinit var instance: App
            private set
    }

}