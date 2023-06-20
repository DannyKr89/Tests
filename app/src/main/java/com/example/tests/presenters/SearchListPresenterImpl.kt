package ru.dk.mydictionary.presenters

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.dk.mydictionary.data.SearchListRepo
import ru.dk.mydictionary.data.model.DictionaryModel
import ru.dk.mydictionary.data.state.AppState
import ru.dk.mydictionary.ui.list.SearchListView

class SearchListPresenterImpl(private val repository: SearchListRepo) : SearchListPresenter {

    override var currentView: SearchListView? = null

    override fun attach(searchView: SearchListView) {
        currentView = searchView
    }

    override fun detach() {
        currentView = null
    }

    override fun requestData(word: String) {
        currentView?.renderData(AppState.Loading)
        repository.getData(word)?.enqueue(object : Callback<List<DictionaryModel>> {
            override fun onResponse(
                call: Call<List<DictionaryModel>>,
                response: Response<List<DictionaryModel>>
            ) {
                if (response.isSuccessful && (response.body() != null)) {
                    currentView?.renderData(AppState.Success(response.body()))
                } else {
                    currentView?.renderData(AppState.Error(Throwable("error")))
                }
            }

            override fun onFailure(call: Call<List<DictionaryModel>>, t: Throwable) {
                currentView?.renderData(AppState.Error(t))
            }
        })
    }
}