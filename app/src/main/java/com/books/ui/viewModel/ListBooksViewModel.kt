package com.books.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.books.data.api.entity.BookSingleApiReturn
import com.books.repository.ListBooksRepository
import kotlinx.coroutines.launch

class ListBooksViewModel: ViewModel() {

    private val repository = ListBooksRepository()
    private var listBooks = MutableLiveData<List<BookSingleApiReturn>>()

    fun search() {
        viewModelScope.launch {
            listBooks.value = repository.syndApi()
        }
    }
    fun getSearch(): MutableLiveData<List<BookSingleApiReturn>> {
        return listBooks
    }
}