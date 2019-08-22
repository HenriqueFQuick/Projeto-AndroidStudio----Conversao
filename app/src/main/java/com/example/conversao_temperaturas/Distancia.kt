package com.example.conversao_temperaturas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_distancia.*
import kotlinx.android.synthetic.main.activity_peso.*

class Distancia : AppCompatActivity(), View.OnClickListener {

    private var tmp : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distancia)

        //verifica constantemente se o radiogroup foi alterado
        radioGroup_Distancia.setOnCheckedChangeListener { _ , checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            //calculo da distancia para cada opcao apresentada
            tmp = when (radio.id) {
                R.id.op_miles -> 1
                else -> {
                    //Evitando erros ( improvavel de cair aqui)
                    Toast.makeText(this, "Opcao Invalida", Toast.LENGTH_SHORT).show()
                    0
                }
            }
        }

        btn_Converter_Distancia.setOnClickListener(this)
        btn_voltar_Distancia_Main.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var temp : Double = edt_Distancia.text.toString().toDouble()
        var result : String = ""

        result = when (tmp) {
            1 -> (temp / 1.609).toString()             //Convertendo para Miles
            else -> {
                //Se nao foi selecionada nenhuma opcao no RadioGroup
                if(v?.id == R.id.btn_Converter_Distancia) {
                    Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                    "000"
                }else "000"
            }
        }

        when(v?.id){
            R.id.btn_Converter_Distancia -> txt_Resultado_Distancia.text = result
            R.id.btn_voltar_Distancia_Main ->{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
