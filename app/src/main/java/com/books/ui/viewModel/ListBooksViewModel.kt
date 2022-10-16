package com.books.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.books.data.api.entity.BookSingleApiReturn
import com.books.repository.ListBooksRepository
import kotlinx.coroutines.launch

class ListBooksViewModel : ViewModel() {

    private val repository = ListBooksRepository()
    private var listBooks = MutableLiveData<List<BookSingleApiReturn>>()
    private var listBooksFirebase = MutableLiveData<List<BookSingleApiReturn>>()

    fun search() {
        searchBooksApi()
        searchBooksFirebase()
    }

    fun searchBooksApi() {
        viewModelScope.launch {
            listBooks.value = repository.syncApi()
        }
    }

    fun getSearch(): MutableLiveData<List<BookSingleApiReturn>> {
        return listBooks
    }

    fun searchBooksFirebase() {
        viewModelScope.launch {
            listBooksFirebase.value = repository.searchFirebase()
        }
    }

    fun getBooksFirebase(): MutableLiveData<List<BookSingleApiReturn>> {
        return listBooksFirebase
    }

    fun removeBook(idBook: Int) {
        listBooksFirebase.value = repository.removeBook(idBook, listBooksFirebase)
    }
    fun addBook(book: BookSingleApiReturn) {
        listBooksFirebase.value = repository.addBook(book, listBooksFirebase)
    }
}