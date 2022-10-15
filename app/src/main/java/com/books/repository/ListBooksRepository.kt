package com.books.repository

import com.books.data.api.entity.BookSingleApiReturn
import com.books.data.api.service.BooksWebService

class ListBooksRepository {
    private val webClient = BooksWebService()

    suspend fun syndApi(): List<BookSingleApiReturn>? {
        return webClient.searchCompleteAllBooksApi()
    }
}