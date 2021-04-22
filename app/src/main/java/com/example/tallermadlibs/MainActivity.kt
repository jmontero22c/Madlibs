package com.example.tallermadlibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.nio.file.OpenOption
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var palabras = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val BotonEmpezar = findViewById<Button>(R.id.empezar)
        BotonEmpezar.setOnClickListener { empezar() }
    }

    fun empezar (){
        val text = resources.openRawResource(R.raw.madlib1_tarzan)
        val text2 = resources.openRawResource(R.raw.madlib0_simple)
        val text3 = resources.openRawResource(R.raw.madlib2_university)
        val text4 = resources.openRawResource(R.raw.madlib4_dance)

        val historias = arrayOf(text, text2, text3, text4)
        val numeroHistoria = (0..3).random()
        val texto_a_leer =historias[numeroHistoria]
        var texto =""

        val scan = Scanner(texto_a_leer)


        while (scan.hasNextLine()){
            texto = scan.nextLine()
            if(texto.contains("<")) {
                var indice =0
                do {
                    val palabraI = texto.indexOf("<",indice)
                    val palabraF = texto.indexOf(">",indice)
                    palabras.add(texto.substring(palabraI + 1, palabraF))

                    indice += palabraF+1
                }while(texto.indexOf("<",palabraF)!=-1)

            }
        }

        val OpenIntent = Intent(this, fill::class.java)
        OpenIntent.putExtra("palabras",palabras)
        OpenIntent.putExtra("numeroHistoria",numeroHistoria)
        startActivity(OpenIntent)

    }


}