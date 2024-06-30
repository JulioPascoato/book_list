package com.example.mybookmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class AddBook : AppCompatActivity() {



    private var name : EditText? = null
    private var pageNumber : EditText? = null
    private var language : EditText? = null
    private var publisher : EditText? = null
    private var gender : EditText? = null
    private var data : EditText? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

       supportActionBar?.title = "Adicionar Novo Livro"

       name = findViewById(R.id.full_title)
       pageNumber = findViewById(R.id.numeroPagina)

       language = findViewById(R.id.idioma)
       publisher = findViewById(R.id.editora)
       gender = findViewById(R.id.genero)
       data = findViewById(R.id.dataPublicacao)

       findViewById<Button>(R.id.btn_save).setOnClickListener {
           val name = name?.text.toString()
           val pageNumber = pageNumber?.text.toString()
           val language = language?.text.toString()
           val publisher = publisher?.text.toString()
           val gender = gender?.text.toString()
           val data = data?.text.toString()

           addBookToDB(name, pageNumber, language, publisher, gender, data)


       }
    }

    private fun addBookToDB(
        name: String,
        pageNumber: String,
        language: String,
        publisher: String,
        gender: String,
        data: String
    ) {
        val map: HashMap<String, Any> = hashMapOf(
            "name" to name,
            "pageNumber" to pageNumber,
            "language" to language,
            "publisher" to publisher,
            "gender" to gender,
            "data" to data,

        )

        val database = Firebase.database
        val bookRef = database.getReference("books")

        val key: String? = bookRef.push().key

        if (key != null) {
            map["key"] = key
        }

        if (key != null) {
            bookRef.child(key).setValue(map).addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    val intent: Intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    val text = "Livro adicionado com sucesso"
                    val duration = Toast.LENGTH_SHORT

                    val toast = Toast.makeText(this, text, duration) // in Activity
                    toast.show()

                }else{
                    val text = "Houve um erro ao cadastrar o book"
                    val duration = Toast.LENGTH_SHORT

                    val toast = Toast.makeText(this, text, duration) // in Activity
                    toast.show()
                }
            }
        }

    }
}