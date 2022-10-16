package com.books.repository

import com.books.data.api.entity.BookSingleApiReturn
import com.books.data.api.service.BooksWebService
import com.books.data.firebase.service.BooksFirebaseService

class ListBooksRepository {
    private val webClient = BooksWebService()
    private val firebase = BooksFirebaseService()

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

}