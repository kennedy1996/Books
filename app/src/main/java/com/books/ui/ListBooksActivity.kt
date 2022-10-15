package com.books.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.books.R
import com.books.data.firebase.service.inicializatorFirebase
import com.books.ui.viewModel.ListBooksViewModel

class ListBooksActivity : AppCompatActivity() {

    private val viewModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(ListBooksViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_books_activity)

        inicializatorFirebase(this)

        viewModel.search()
        viewModel.searchBooksFirebase()

        val button = findViewById<Button>(R.id.list_books_activity_button_update)
        button.setOnClickListener {
            Log.i("testUpdate", "onCreate: ${viewModel.getSearch().value?.size}")
            val valor = viewModel.getSearch().value
            if (valor != null) {
                for(i in valor.indices){
                    Log.i("testUpdate", "ID: ${valor[i].id} | Description: ${valor[i].title}\n\n")
                }
            }
            Log.i("testUpdate", "TamanhoFirebase: ${viewModel.getBooksFirebase().value?.size}")
            val valor2 = viewModel.getBooksFirebase().value
            if (valor2 != null) {
                for(i in valor2.indices){
                    Log.i("testUpdateFirebase", "ID: ${valor2[i].id} | Title: ${valor2[i].title}\n\n")
                }
            }


        }

    }
}