package com.books.repository

import androidx.lifecycle.MutableLiveData
import com.books.data.api.entity.BookSingleApiReturn
import com.books.data.api.service.BooksWebService
import com.books.data.firebase.service.BooksFirebaseService

class ListBooksRepository {
    private val webClient = BooksWebService()
    private val firebase = BooksFirebaseService()
    private val manageList = ManageList()

    suspend fun syncApi(): List<BookSingleApiReturn>? {
        var search = webClient.searchCompleteAllBooksApi()
        if (search != null) {
            syncApiToFirebase(search)
        }
        return search
    }

    fun syncApiToFirebase(list: List<BookSingleApiReturn>?) {
        firebase.sendBooksToFirebase(list)
    }

    fun searchFirebase(): List<BookSingleApiReturn>? {
        return firebase.searchFirebaseData()
    }

    fun removeBook(
        positionList: Int,
        listBooksFirebase: MutableLiveData<List<BookSingleApiReturn>>
    ): List<BookSingleApiReturn>? {
        return manageList.removeBook(positionList, listBooksFirebase)
    }

    fun addBook(
        book: BookSingleApiReturn,
        listBooksFirebase: MutableLiveData<List<BookSingleApiReturn>>
    ): List<BookSingleApiReturn>? {
        return manageList.addBook(book, listBooksFirebase)
    }

    fun modifyBook(
        book: BookSingleApiReturn,
        listBooksFirebase: MutableLiveData<List<BookSingleApiReturn>>,
        position: Int
    ): List<BookSingleApiReturn>? {
        return manageList.modifyBook(book, listBooksFirebase, position)
    }

}