package com.example.tests.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import ru.dk.mydictionary.data.SearchListRepo
import ru.dk.mydictionary.data.state.AppState

class SearchListViewModel(private val repository: SearchListRepo) : ViewModel() {

    private val _liveData = MutableLiveData<AppState>()
    private val liveData: LiveData<AppState> = _liveData

    fun getLiveData() = liveData

    fun requestData(word: String) {
        _liveData.postValue(AppState.Loading)
        viewModelScope.launch() {
            repository.getData(word)
                ?.catch {
                    _liveData.postValue(AppState.Error(it))
                }
                ?.collect {
                    if (it.isEmpty()) {
                        _liveData.postValue(AppState.Error(Throwable("empty")))
                    } else {
                        _liveData.postValue(AppState.Success(it))
                    }
                }
        }
    }
}