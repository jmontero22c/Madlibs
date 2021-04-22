package com.example.tallermadlibs

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class fill : AppCompatActivity() {
    var palabrasGuardadas = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill)

        val palabras = getIntent().getSerializableExtra("palabras") as ArrayList<String>

        val button = findViewById<Button>(R.id.enviar)
        val editText = findViewById<EditText>(R.id.llenar)
        editText.hint = palabras[0]

        println(palabras.size)

        println(palabras)
        button.setOnClickListener { enviar(palabras) }
        println(palabrasGuardadas)
    }

    fun enviar(palabras:ArrayList<String>){
        val numeroHistoria = getIntent().getSerializableExtra("numeroHistoria") as Int
        var posicion = palabrasGuardadas.size + 1
        val editText = findViewById<EditText>(R.id.llenar)

        if(posicion == palabras.size){
            palabrasGuardadas.add(editText.text.toString())
            val OpenIntent = Intent(this, final_text::class.java)
            OpenIntent.putExtra("palabras",palabras)
            OpenIntent.putExtra("palabrasGuardadas",palabrasGuardadas)
            OpenIntent.putExtra("numeroHistoria",numeroHistoria)
            startActivity(OpenIntent)
        }else{
            palabrasGuardadas.add(editText.text.toString())
            editText.hint = palabras[posicion]
            editText.text=null
        }
    }
}