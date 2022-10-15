package com.books.data.firebase.converter

import android.util.Log
import com.books.data.api.entity.BookSingleApiReturn

class DocumentConverter(
    val id: Int = 0,
    val title: String = "",
    val isbn: String = "",
    val description: String = "",
    val price: Int = 0,
    val currencyCode: String = "",
    val author: String = ""
) {
    fun forTrip(id: String): BookSingleApiReturn {

        Log.i("CCDocs", "$id |  $title | $description")

        return BookSingleApiReturn(
            id = id.toInt(),
            title = title,
            isbn = isbn,
            description = description,
            price = price,
            currencyCode = currencyCode,
            author = author
        )
    }

}