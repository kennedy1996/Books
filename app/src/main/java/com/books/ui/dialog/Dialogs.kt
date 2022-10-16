package com.books.ui.dialog

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.books.R
import com.books.data.api.entity.BookSingleApiReturn
import com.books.ui.recyclerView.ListBooksAdapter
import com.books.ui.viewModel.ListBooksViewModel

fun dialogNewBook(
    context: Context,
    adapter: RecyclerView.Adapter<ListBooksAdapter.ViewHolder>?,
    viewModel: ListBooksViewModel,
) {
    val dialog = Dialog(context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(true)
    dialog.setContentView(R.layout.dialog_books_add_modify)
    dialog.show()
    val title: EditText = dialog.findViewById(R.id.dialog_books_add_modify_title_field)
    val description: EditText =
        dialog.findViewById(R.id.dialog_books_add_modify_description_field)
    val price: EditText = dialog.findViewById(R.id.dialog_books_add_modify_price_field)
    val isbn: EditText = dialog.findViewById(R.id.dialog_books_add_modify_isbn_field)
    val author: EditText = dialog.findViewById(R.id.dialog_books_add_modify_author_field)
    val currencyCode: EditText =
        dialog.findViewById(R.id.dialog_books_add_modify_currencyCode_field)

    val button: Button = dialog.findViewById(R.id.dialog_books_add_modify_button_save)

    var error = false

    button.setOnClickListener {
        error = checkDataForm(title, error, description, price, isbn, author)
        if (!error) {
            val book = BookSingleApiReturn(
                id = 0,
                title = title.text.toString(),
                description = description.text.toString(),
                price = price.text.toString().toInt(),
                isbn = isbn.text.toString(),
                author = author.text.toString(),
                currencyCode = currencyCode.text.toString()
            )
            viewModel.addBook(book)
            adapter!!.notifyDataSetChanged()
            dialog.dismiss()
        }
    }
}

private fun checkDataForm(
    title: EditText,
    error: Boolean,
    description: EditText,
    price: EditText,
    isbn: EditText,
    author: EditText
): Boolean {
    var error1 = error
    if (title.text.isNullOrBlank()) {
        title.error = "Invalid Title"
        error1 = true
    }
    if (description.text.isNullOrBlank()) {
        description.error = "Invalid Description"
        error1 = true
    }
    if (price.text.isNullOrBlank()) {
        price.error = "Invalid Price"
        error1 = true
    }
    if (isbn.text.isNullOrBlank()) {
        isbn.error = "Invalid ISBN"
        error1 = true
    }
    if (author.text.isNullOrBlank()) {
        author.error = "Invalid Author"
        error1 = true
    }
    return error1
}