package com.books.data.firebase.service

import com.books.data.api.entity.BookSingleApiReturn
import com.books.data.firebase.converter.DocumentConverter
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

class BooksFirebaseService {

    private val db = FirebaseFirestore.getInstance(Firebase.app("bd-firebase"))
    private val collection = db.collection("books")

    private fun convertToBook(documento: DocumentSnapshot): BookSingleApiReturn? =
        documento.toObject(DocumentConverter::class.java)?.forTrip(documento.id)

    fun sendBooksToFirebase(list: List<BookSingleApiReturn>?){
        for(i in list?.indices!!){
            var list = list[i]
            val documento = list.id?.let { id ->
                collection.document(id.toString())
            } ?: collection.document()
            documento.set(list)
        }
    }
    fun searchFirebaseData(): List<BookSingleApiReturn>{
        val list = mutableListOf<BookSingleApiReturn>()
        collection
            .addSnapshotListener { snapshot, _ ->
                snapshot?.let { snapshot ->
                    val books: List<BookSingleApiReturn> = snapshot.documents
                        .mapNotNull { documento ->
                            convertToBook(documento)
                        }
                    books.forEach { produtos ->
                        val book = BookSingleApiReturn(
                            produtos.id,
                            produtos.title,
                            produtos.isbn,
                            produtos.description,
                            produtos.price,
                            produtos.currencyCode,
                            produtos.author
                        )
                        list.add(book)
                    }
                }
            }
        return list
    }
}