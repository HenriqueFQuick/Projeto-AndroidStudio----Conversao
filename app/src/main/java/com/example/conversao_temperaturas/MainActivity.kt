package com.example.conversao_temperaturas

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_temperatura.setOnClickListener(this)
        btn_altura.setOnClickListener(this)
        btn_peso.setOnClickListener(this)
        btn_distancia.setOnClickListener(this)
        btn_generico.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        //verificar qual botao foi clicado
        when (v?.id) {

            //redirecionar para a activity Temperatura
            R.id.btn_temperatura -> {
                val intent = Intent(this, Temperatura::class.java)
                startActivity(intent)
            }
            //redirecionar para a activity Altura
            R.id.btn_altura -> {
                val intent = Intent(this, Altura::class.java)
                startActivity(intent)
            }
            //redirecionar para a activity Peso
            R.id.btn_peso -> {
                val intent = Intent(this, Peso::class.java)
                startActivity(intent)
            }
            //redirecionar para a activity Distancia
            R.id.btn_distancia -> {
                val intent = Intent(this, Distancia::class.java)
                startActivity(intent)
            }
            //redirecionar para a activity Generico
            R.id.btn_generico -> {
                val intent = Intent(this, Generico::class.java)
                startActivity(intent)
            }
        }
    }
}

