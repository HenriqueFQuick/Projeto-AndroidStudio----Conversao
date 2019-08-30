package com.example.conversao_temperaturas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_altura.*
import kotlinx.android.synthetic.main.activity_distancia.*
import kotlinx.android.synthetic.main.activity_peso.*

class Distancia : AppCompatActivity(), View.OnClickListener {

    private var tmp1 : Int = 0
    private var tmp2 : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distancia)

        //verifica constantemente se o radiogroup1 foi alterado
        radioGroup_Distancia1.setOnCheckedChangeListener { _ , checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            //calculo da distancia para cada opcao apresentada
            tmp1 = when (radio.id) {
                R.id.op_miles1 -> 1 //Convertendo a partir de miles
                R.id.op_Km1 -> 2    //Convertendo a partir de Km
                else -> {
                    //Evitando erros ( improvavel de cair aqui)
                    Toast.makeText(this, "Opcao Invalida", Toast.LENGTH_SHORT).show()
                    0
                }
            }
        }

        //verifica constantemente se o radiogroup2 foi alterado
        radioGroup_Distancia2.setOnCheckedChangeListener { _ , checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            //calculo da distancia para cada opcao apresentada
            tmp2 = when (radio.id) {
                R.id.op_miles2 -> 1 //Convertendo para miles
                R.id.op_Km2 -> 2    //Convertendo para Km
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

        var temp : Double? = 0.0
        try {
            temp = edt_Distancia.text.toString().toDouble()
        }catch (e: Exception){
            //Se nao foi digitado nenhum valor
            Toast.makeText(this, "Digite um valor", Toast.LENGTH_LONG).show()
            temp = temp ?: 0.0
        }
        var result : String = ""

        result = when (tmp1) {
            1 -> { //Miles
                when(tmp2){
                    1 -> temp.toString()             //Miles para Miles
                    2 -> (temp!! * 1.609).toString() //Miles para Km
                    else -> {
                        Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                        "0" //Se nao foi selecionada uma medida para conversao
                    }
                }
            }
            2 -> { //Km
                when(tmp2){
                    1 -> (temp!! / 1.609).toString() //Km para Miles
                    2 -> temp.toString()             //Km para Km
                    else -> {
                        Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                        "0" //Se nao foi selecionada uma medida para conversao
                    }
                }
            }
            else -> {
                //Se nao foi selecionada nenhuma opcao no RadioGroup
                if(v?.id == R.id.btn_Converter_Distancia) {
                    Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                    "0"
                }else "0"
            }
        }

        //verificar qual botao foi pressionado
        when(v?.id){
            //setar a resposta da conversao no resultado
            R.id.btn_Converter_Distancia -> txt_Resultado_Distancia.text = result

            //redirecionar para a activity Main
            R.id.btn_voltar_Distancia_Main -> finish()
        }
    }
}
