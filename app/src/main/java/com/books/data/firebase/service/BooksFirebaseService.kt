package com.books.data.firebase.service

import com.books.data.api.entity.BookSingleApiReturn
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

class BooksFirebaseService {

    private val db = FirebaseFirestore.getInstance(Firebase.app("bd-firebase"))
    private val collection = db.collection("books")

    fun sendBooksToFirebase(list: List<BookSingleApiReturn>?){
        for(i in list?.indices!!){
            var list = list[i]
            val documento = list.id?.let { id ->
                collection.document(id.toString())
            } ?: collection.document()
            documento.set(list)
        }
    }
}