package ru.dk.mydictionary.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tests.databinding.SearchItemBinding
import ru.dk.mydictionary.data.model.DictionaryModel

class SearchListAdapter :
    ListAdapter<DictionaryModel, SearchListAdapter.SearchListViewHolder>(SearchListCallback()) {

    inner class SearchListViewHolder(val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dictionaryModel: DictionaryModel) {
            with(binding) {
                titleSearchItem.text = dictionaryModel.text
                descriptionSearchItem.text = dictionaryModel.meanings?.first()?.translation?.text
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}