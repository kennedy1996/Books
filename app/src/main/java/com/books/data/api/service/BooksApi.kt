package com.books.data.api.service

import com.books.data.api.entity.BookSingleApiReturn
import com.books.data.api.entity.BooksApiReturn
import retrofit2.http.GET
import retrofit2.http.Path

interface BooksApi {

    @GET("books")
    suspend fun search(): List<BooksApiReturn>

    @GET("book/{bookId}")
    suspend fun searchBook(@Path("bookId") bookId: Int): BookSingleApiReturn
}