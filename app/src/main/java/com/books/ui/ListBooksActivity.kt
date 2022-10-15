package com.books.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.books.R
import com.books.ui.viewModel.ListBooksViewModel

class ListBooksActivity : AppCompatActivity() {

    private val viewModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(ListBooksViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_books_activity)

        viewModel.search()

        val button = findViewById<Button>(R.id.activity_trip_update)
        button.setOnClickListener {
            Log.i("1testeUp", "onCreate: ${viewModel.getSearch().value?.size}")
            val valor = viewModel.getSearch().value
            if (valor != null) {
                for(i in valor.indices){
                    Log.i("1testeUp", "ID: ${valor[i].id} | Description: ${valor[i].description}\n\n")
                }
            }
        }

    }
}