package com.example.conversao_temperaturas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_altura.*
import kotlinx.android.synthetic.main.activity_distancia.*
import kotlinx.android.synthetic.main.activity_peso.*

class Distancia : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private var tmp1 : String = ""
    private var tmp2 : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distancia)

        ArrayAdapter.createFromResource(this, R.array.opcoes_Distancia1, android.R.layout.simple_spinner_item).also{ adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_Distancia1.adapter = adapter
        }
        ArrayAdapter.createFromResource(this, R.array.opcoes_Distancia2, android.R.layout.simple_spinner_item).also{ adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_Distancia2.adapter = adapter
        }

        btn_Converter_Distancia.setOnClickListener(this)
        btn_voltar_Distancia_Main.setOnClickListener(this)
        spinner_Distancia1.onItemSelectedListener = this
        spinner_Distancia2.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id){
            R.id.spinner_Distancia1 -> tmp1 = parent.getItemAtPosition(position).toString()
            R.id.spinner_Distancia2 -> tmp2 = parent.getItemAtPosition(position).toString()
            else -> Toast.makeText(this, "Opcao Invalida", Toast.LENGTH_LONG).show()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        tmp1 = "Km"
        tmp2 = "Miles"
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
            "Miles" -> { //Miles
                when(tmp2){
                    "Miles" -> temp.toString()             //Miles para Miles
                    "Km" -> (temp!! * 1.609).toString() //Miles para Km
                    else -> {
                        Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                        "0" //Se nao foi selecionada uma medida para conversao
                    }
                }
            }
            "Km" -> { //Km
                when(tmp2){
                    "Miles" -> (temp!! / 1.609).toString() //Km para Miles
                    "Km" -> temp.toString()             //Km para Km
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
