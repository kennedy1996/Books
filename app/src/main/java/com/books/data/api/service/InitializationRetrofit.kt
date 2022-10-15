package com.books.data.api.service

import com.books.data.api.entity.BooksApiReturn
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class InitializationRetrofit {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://tpbookserver.herokuapp.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val booksApi = retrofit.create(BooksApi::class.java)
}