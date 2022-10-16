package com.books.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.books.R
import com.books.data.firebase.service.inicializatorFirebase
import com.books.ui.recyclerView.ListBooksAdapter
import com.books.ui.viewModel.ListBooksViewModel

class ListBooksActivity : AppCompatActivity() {

    private val viewModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(ListBooksViewModel::class.java)
    }

    private var adapter: RecyclerView.Adapter<ListBooksAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_books_activity)

        inicializatorFirebase(this)
        searchingData()
        settingsRecyclerView()
        observersToLiveData()
        checkDataAfterTime()
        onClickUpdateButton()

    }

    private fun onClickUpdateButton() {
        val updateButton = findViewById<Button>(R.id.list_books_activity_button_update)
        updateButton.setOnClickListener {
            settingsRecyclerView()
            adapter!!.notifyDataSetChanged()
        }
    }

    private fun searchingData() {
        viewModel.search()
        viewModel.searchBooksFirebase()
    }

    private fun settingsRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.list_books_activity_recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ListBooksAdapter(
            this,
            viewModel.getBooksFirebase()
        )
        recyclerView.adapter = adapter
    }
    private fun observersToLiveData() {
        viewModel.getBooksFirebase()?.observe(this, Observer {
            adapter?.notifyDataSetChanged()
        })
    }
    private fun checkDataAfterTime() {
        val h = Handler()
        h.postDelayed({
            if (viewModel.getBooksFirebase().value?.size != 0) {
                adapter!!.notifyDataSetChanged()
            }
        }, 2000)
    }
}