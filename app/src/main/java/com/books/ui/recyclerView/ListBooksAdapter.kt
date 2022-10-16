package com.books.ui.recyclerView

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.books.R
import com.books.data.api.entity.BookSingleApiReturn

class ListBooksAdapter(
    private val context: Context,
    private var list: MutableLiveData<List<BookSingleApiReturn>>,
) : RecyclerView.Adapter<ListBooksAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.list_books_activity_item_title_content)
        val description: TextView =
            itemView.findViewById(R.id.list_books_activity_item_description_content)
        val isbn: TextView = itemView.findViewById(R.id.list_books_activity_item_isbn_content)
        val price: TextView = itemView.findViewById(R.id.list_books_activity_item_price_content)
        val author: TextView = itemView.findViewById(R.id.list_books_activity_item_author_content)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListBooksAdapter.ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.list_books_activity_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            var listAdapter = list?.value?.get(position)
            holder.title.text = listAdapter?.title
            holder.description.text = listAdapter?.description
            holder.isbn.text = listAdapter?.isbn
            holder.price.text = "${listAdapter?.price} ${listAdapter?.currencyCode}"
            holder.author.text = listAdapter?.author

           holder.description.setOnClickListener{
               showDialogFullDescription(listAdapter?.description.toString())
           }


        } catch (e: Exception) {
            Log.e("ListBooksAdapter", e.message.toString())
        }
    }

    override fun getItemCount(): Int {
        return list?.value?.count() ?: 0
    }

    fun showDialogFullDescription(description: String) {
        var alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Description")
            .setMessage(description)
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

    inner class ViewHolder2() {
        fun getItemCount(): Int {
            return list?.value?.count() ?: 0
        }
    }
}