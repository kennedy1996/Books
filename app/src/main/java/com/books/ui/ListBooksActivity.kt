package com.books.ui

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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

    private var updateSwipe: SwipeRefreshLayout? = null

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
        updateSwipe!!.setOnRefreshListener {
            searchingData()
            adapter!!.notifyDataSetChanged()
            checkDataAfterTime()
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

        settingsSwipe(recyclerView)
    }

    private fun settingsSwipe(recyclerView: RecyclerView) {
        updateSwipe = findViewById<SwipeRefreshLayout>(R.id.list_books_activity_swipe)
        val swipeHandler = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.START or ItemTouchHelper.END
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.removeBook(viewHolder.adapterPosition)
                adapter!!.notifyItemRemoved(viewHolder.adapterPosition)
                adapter!!.notifyDataSetChanged()
            }
        }
        val itemTouch = ItemTouchHelper(swipeHandler)
        itemTouch.attachToRecyclerView(recyclerView)
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
                updateSwipe!!.isRefreshing = false
            }
        }, 2000)
    }
}