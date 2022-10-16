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

        viewModel.search()
        viewModel.searchBooksFirebase()

        settingsRecyclerView()
        observersToLiveData()

        checkDataAfterTime()

        Log.i("adapterCount", "0adapter..count ${  adapter!!.itemCount}")

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
            Log.i("adapterCount", "1adapter..count ${  adapter!!.itemCount}")
            adapter!!.notifyDataSetChanged()


        }

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
            Log.i("adapterCount", "2adapter..count ${  adapter!!.itemCount}")
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