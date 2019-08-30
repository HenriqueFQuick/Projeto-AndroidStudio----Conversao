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
import kotlinx.android.synthetic.main.activity_peso.*
import kotlinx.android.synthetic.main.activity_temperatura.*

class Temperatura : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private var tmp1 : String = ""
    private var tmp2 : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperatura)

        ArrayAdapter.createFromResource(this, R.array.opcoes_Temperatura1, android.R.layout.simple_spinner_item).also{ adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_Temperatura1.adapter = adapter
        }
        ArrayAdapter.createFromResource(this, R.array.opcoes_Temperatura2, android.R.layout.simple_spinner_item).also{ adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_Temperatura2.adapter = adapter
        }

        //listener do botao ( redireciona para o metodo onClick)
        btn_Converter_Temperatura.setOnClickListener(this)
        btn_voltar_Temperatura_Main.setOnClickListener(this)
        spinner_Temperatura1.onItemSelectedListener = this
        spinner_Temperatura2.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id){
            R.id.spinner_Temperatura1 -> tmp1 = parent.getItemAtPosition(position).toString()
            R.id.spinner_Temperatura2 -> tmp2 = parent.getItemAtPosition(position).toString()
            else -> Toast.makeText(this, "Opcao Invalida", Toast.LENGTH_LONG).show()
        }
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        tmp1 = "Celsius"
        tmp2 = "Kelvin"
    }

    //metodo ativado quando o botao eh pressionado
    //metodo coloca o resultado da conta pela temperatura selecionada no txt_Resultado
    override fun onClick(v: View?) {
        // guarda o valor digitado no edt_Temperatura1
        var temp : Double? = 0.0
        try {
            temp = edt_Temperatura.text.toString().toDouble()
        }catch (e: Exception){
            //Se nao foi digitado nenhum valor
            Toast.makeText(this, "Digite um valor", Toast.LENGTH_LONG).show()
            temp = temp ?: 0.0
        }
        var result : String = ""

        result = when (tmp1) {
            "Kelvin" -> { //Kelvin
                when(tmp2){
                    "Kelvin" -> (temp).toString()                                  //Kelvin para Kelvin
                    "Fahrenheit" -> (((temp!! - 273.15)* 9 / 5) + 32).toString()       //Kelvin para Fahrenheit
                    "Reaumur" -> ((temp!! - 273.15) * 4/5).toString()               //Kelvin para Reaumur
                    "Rankine" -> (((temp!! - 273.15) * 9 / 5) + 491.67).toString()  //Kelvin para Rankine
                    "Celsius" -> (temp!! - 273.15).toString()                       //Kelvin para Celsius
                    else -> {
                        Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_SHORT).show()
                        "0"
                    }
                }
            }
            "Fahrenheit" -> {  //Fahrenheit
                when(tmp2){
                    "Kelvin" -> (((temp!! / 9 / 5) - 32) + 273.15).toString()              //Fahrenheit para Kelvin
                    "Fahrenheit" -> (temp).toString()                                          //Fahrenheit para Fahrenheit
                    "Reaumur" -> (((temp!! / 9 / 5) - 32) * 4/5).toString()                 //Fahrenheit para Reaumur
                    "Rankine" -> ((((temp!! / 9 / 5) - 32) * 9 / 5) + 491.67).toString()    //Fahrenheit para Rankine
                    "Celsius" -> ((temp!! * 9 / 5) + 32).toString()                         //Fahrenheit para Celsius
                    else -> {
                        Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_SHORT).show()
                        "0"
                    }
                }
            }
            "Reaumur" -> {  //Reaumur
                when(tmp2){
                    "Kelvin" -> ((temp!! / 4/5) + 273.15).toString()           //Reaumur para Kelvin
                    "Fahrenheit" -> (((temp!! / 4/5)* 9 / 5) + 32).toString()      //Reaumur para Fahrenheit
                    "Reaumur" -> (temp).toString()                              //Reaumur para Reaumur
                    "Rankine" -> (((temp!! / 4/5) * 9 / 5) + 491.67).toString() //Reaumur para Rankine
                    "Celsius" -> (temp!! / 4/5).toString()                      //Reaumur para Celsius
                    else -> {
                        Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_SHORT).show()
                        "0"
                    }
                }
            }
            "Rankine" -> {  //Rankine
                when(tmp2){
                    "Kelvin" -> (((temp!! / 9 / 5) - 491.67) + 273.15).toString()      //Rankine para Kelvin
                    "Fahrenheit" -> ((((temp!! / 9 / 5) - 491.67)* 9 / 5) + 32).toString() //Rankine para Fahrenheit
                    "Reaumur" -> (((temp!! / 9 / 5) - 491.67) * 4/5).toString()         //Rankine para Reaumur
                    "Rankine" -> (temp).toString()                                      //Rankine para Rankine
                    "Celsius" -> ((temp!! / 9 / 5) - 491.67).toString()                 //Rankine para Celsius
                    else -> {
                        Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_SHORT).show()
                        "0"
                    }
                }
            }
            "Celsius" -> {  //Celsius
                when(tmp2){
                    "Kelvin" -> (temp!! + 273.15).toString()           //Celsius para Kelvin
                    "Fahrenheit" -> ((temp!! * 9 / 5) + 32).toString()     //Celsius para Fahrenheit
                    "Reaumur" -> (temp!! * 4 / 5).toString()            //Celsius para Reaumur
                    "Rankine" -> ((temp!! * 9 / 5) + 491.67).toString() //Celsius para Rankine
                    "Celsius" -> (temp).toString()                      //Celsius para Celsius
                    else -> {
                        Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_SHORT).show()
                        "0"
                    }
                }
            }
            else -> {
                //Se nao foi selecionada nenhuma opcao no RadioGroup
                if(v?.id == R.id.btn_Converter_Temperatura) {
                    Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                    "0"
                }else "0"
            }
        }

        //verificar qual botao foi pressionado
        when(v?.id){
            //setar a resposta da conversao no resultado
            R.id.btn_Converter_Temperatura -> txt_Resultado_Temperatura.text = result

            //redirecionar para a activity Main
            R.id.btn_voltar_Temperatura_Main -> finish()
        }
    }
}
