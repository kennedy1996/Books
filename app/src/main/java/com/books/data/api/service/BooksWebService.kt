package com.books.data.api.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.books.data.api.entity.BookSingleApiReturn
import com.books.data.api.entity.BooksApiReturn

private const val TAG = "BooksWebService"

class BooksWebService {

    private val booksApiService: BooksApi =
        InitializationRetrofit().booksApi

    suspend fun searchAllBooksApi(): List<BooksApiReturn>? {
        var returnV: List<BooksApiReturn>? = null
        try {
            val booksReturn = booksApiService
                .search()
            returnV = booksReturn
        } catch (e: Exception) {
            Log.e(TAG, "searchListBooksApi: ", e)
        }
        return returnV
    }

    suspend fun searchSingleBookApi(idBook: Int): BookSingleApiReturn? {
        var returnV: BookSingleApiReturn? = null
        try {
            val booksReturn = booksApiService
                .searchBook(idBook)
            returnV = booksReturn

        } catch (e: Exception) {
            Log.e(TAG, "searchSingleBookApi: ", e)
        }
        return returnV
    }

    suspend fun searchCompleteAllBooksApi(): List<BookSingleApiReturn>? {
        var returnV: List<BookSingleApiReturn>? = null
        val completeList = mutableListOf<BookSingleApiReturn>()
        try {
            val listBooks = searchAllBooksApi()

            if(listBooks!=null){
                for(i in listBooks.indices){
                    val detailsBook = searchSingleBookApi(listBooks[i].id)
                    if(detailsBook!=null){
                    completeList.add(detailsBook)
                    }else{
                        val withoutDetailBook = BookSingleApiReturn(
                            listBooks[i].id,
                            listBooks[i].title,
                            listBooks[i].isbn,
                            "Description not available",
                            listBooks[i].price,
                            listBooks[i].currencyCode,
                            listBooks[i].author
                        )
                        completeList.add(withoutDetailBook)
                    }
                }
                returnV = completeList
            }
        } catch (e: Exception) {
            Log.e(TAG, "searchListBooksApi: ", e)
        }
        return returnV
    }
}