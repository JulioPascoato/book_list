package com.example.mybookmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookViewAdapter(val listBook: List<Book>): RecyclerView.Adapter<BookViewAdapter.BookViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.book_item,
            parent,
            false
        )
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listBook.count()
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(listBook[position])
    }

    inner class BookViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val tvName : TextView = view.findViewById(R.id.name_textView)
        private val tvNumberPage : TextView = view.findViewById(R.id.numeroPagina_textView)
        private val tvGender : TextView = view.findViewById(R.id.genero_textView)

        fun bind(book: Book){
            tvName.text = book.name
            tvNumberPage.text = "Páginas: ${book.pageNumber}"
            tvGender.text = book.gender
        }
    }
}