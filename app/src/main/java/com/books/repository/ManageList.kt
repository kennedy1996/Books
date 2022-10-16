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


}