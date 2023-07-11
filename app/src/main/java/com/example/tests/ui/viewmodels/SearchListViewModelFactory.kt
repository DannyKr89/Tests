package com.example.tests.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import ru.dk.mydictionary.data.SearchListRepo

class SearchListViewModelFactory(private val repo: SearchListRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return SearchListViewModel(repo) as T
    }
}