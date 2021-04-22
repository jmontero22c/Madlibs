package com.example.tallermadlibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class final_text : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_text)
        val palabras = getIntent().getSerializableExtra("palabras") as ArrayList<String>
        val palabrasGuardadas = getIntent().getSerializableExtra("palabrasGuardadas") as ArrayList<String>
        val tryAgain = findViewById<Button>(R.id.tryAgain)

        mostrarTexto(palabras, palabrasGuardadas)
        tryAgain.setOnClickListener { again() }

    }
    fun mostrarTexto(palabras:ArrayList<String>, palabrasGuardadas: ArrayList<String>){
        val numeroHistoria = getIntent().getSerializableExtra("numeroHistoria") as Int

        val text = resources.openRawResource(R.raw.madlib1_tarzan)
        val text2 = resources.openRawResource(R.raw.madlib0_simple)
        val text3 = resources.openRawResource(R.raw.madlib2_university)
        val text4 = resources.openRawResource(R.raw.madlib4_dance)

        val historias = arrayOf(text, text2, text3, text4)
        val texto_a_leer =historias[ numeroHistoria ]


        val text_layout = findViewById<TextView>(R.id.textView)
        var i=0
        var texto =""
        val scan = Scanner(texto_a_leer)
        while (scan.hasNextLine()) {
            texto += scan.nextLine()

            if(texto.contains("<")) {
                var indice = 0
                do {
                    val palabraI = texto.indexOf("<", indice)
                    val palabraF = texto.indexOf(">", indice)
                    val palabraCambiar = texto.substring(palabraI, (palabraF +1))
                    texto = texto.replace(palabraCambiar, palabrasGuardadas[i])
                    text_layout.text = texto
                    i++
                    indice += palabraF + 1
                } while (texto.indexOf("<", palabraF) != -1)
            }
        }


    }
    fun again(){
        finish()
        val OpenIntent = Intent(this, MainActivity::class.java)
        startActivity(OpenIntent)
    }
}