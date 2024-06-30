package com.example.mybookmanager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

             supportActionBar?.title = "Lista de Livros"

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("books")
        val recyclerView: RecyclerView = findViewById(R.id.books_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, 1))


        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val list = ArrayList<Book>()

                for(book in snapshot.children){
                    val data = book.child("data").value
                    val gender = book.child("gender").value
                    val key = book.child("key").value
                    val language = book.child("language").value
                    val name = book.child("name").value
                    val pageNumber = book.child("pageNumber").value
                    val publisher = book.child("publisher").value
                    val newBook = Book(
                        key.toString(),
                        name.toString(),
                        pageNumber.toString(),
                        language.toString(),
                        publisher.toString(),
                        gender.toString(),
                        data.toString()
                    )
                    list.add(newBook)
                }
                recyclerView.adapter = BookViewAdapter(list)


            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("cancel", error.toString())
            }

        })















        findViewById<View>(R.id.fab).setOnClickListener {
            val intent: Intent = Intent(this,AddBook::class.java)
            startActivity(intent)
        }




    }

    private fun getData(){

    }



}