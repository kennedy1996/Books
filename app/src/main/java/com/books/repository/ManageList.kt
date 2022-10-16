package com.books.repository

import androidx.lifecycle.MutableLiveData
import com.books.data.api.entity.BookSingleApiReturn

class ManageList {

    fun removeBook(positionList: Int, listBooksFirebase: MutableLiveData<List<BookSingleApiReturn>>): List<BookSingleApiReturn>? {
        val list = mutableListOf<BookSingleApiReturn>()
        list.addAll(listBooksFirebase.value!!)
        list.removeAt(positionList)
        return list
    }

    fun addBook(bookRecibed: BookSingleApiReturn, listBooksFirebase: MutableLiveData<List<BookSingleApiReturn>>): List<BookSingleApiReturn>? {
        val list = mutableListOf<BookSingleApiReturn>()
        list.addAll(listBooksFirebase.value!!)
        val book = BookSingleApiReturn(
            id = nextId(list),
            title = bookRecibed.title,
            description = bookRecibed.description,
            price = bookRecibed.price,
            isbn = bookRecibed.isbn,
            author = bookRecibed.author,
            currencyCode = bookRecibed.currencyCode
        )
        list.add(book)
        return list
    }
    fun nextId(list: MutableList<BookSingleApiReturn>): Int {
        var higher= 0
        for (i in list.indices){
            if(list[i].id>higher){
                higher=list[i].id
            }
        }
        return higher+100
    }


}