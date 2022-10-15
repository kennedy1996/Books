package com.books.data.api.entity

class BooksApiReturn (
    val id: Int,
    val title: String,
    val isbn: String,
    val price: Int,
    val currencyCode: String,
    val author: String
    )

class BookSingleApiReturn (
    val id: Int,
    val title: String,
    val isbn: String,
    val description: String,
    val price: Int,
    val currencyCode: String,
    val author: String
)