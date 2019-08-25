package com.example.conversao_temperaturas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_altura.*
import kotlinx.android.synthetic.main.activity_temperatura.*

class Temperatura : AppCompatActivity(), View.OnClickListener {

    private var tmp1 : Int = 0
    private var tmp2 : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperatura)

        //verifica constantemente se o radiogroup1 foi alterado
        radioGroup_Temperatura1.setOnCheckedChangeListener { _ , checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            //calculo da temperatura para cada opcao apresentada
            tmp1 = when (radio.id) {
                R.id.op_Kelvin1 -> 1     //Convertendo a partir de Kelvin
                R.id.op_Fahrenheit1 -> 2 //Convertendo a partir de Fahrenheit
                R.id.op_Reaumur1 -> 3    //Convertendo a partir de Reaumur
                R.id.op_Rankine1 -> 4    //Convertendo a partir de Rankine
                R.id.op_Celsius1 -> 5    //Convertendo a partir de Celsius
                else -> {
                    //Evitando erros ( improvavel de cair aqui)
                    Toast.makeText(this, "Opcao Invalida", Toast.LENGTH_SHORT).show()
                    0
                }
            }
        }
        //verifica constantemente se o radiogroup2 foi alterado
        radioGroup_Temperatura2.setOnCheckedChangeListener { _ , checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            //calculo da temperatura para cada opcao apresentada
            tmp2 = when (radio.id) {
                R.id.op_Kelvin2 -> 1      //Convertendo para Kelvin
                R.id.op_Fahrenheit2 -> 2  //Convertendo para Fahrenheit
                R.id.op_Reaumur2 -> 3     //Convertendo para Reaumur
                R.id.op_Rankine2 -> 4     //Convertendo para Rankine
                R.id.op_Celsius2 -> 5     //Convertendo para Celsius
                else -> {
                    //Evitando erros ( improvavel de cair aqui)
                    Toast.makeText(this, "Opcao Invalida", Toast.LENGTH_SHORT).show()
                    0
                }
            }
        }

        //listener do botao ( redireciona para o metodo onClick)
        btn_Converter_Temperatura.setOnClickListener(this)
        btn_voltar_Temperatura_Main.setOnClickListener(this)
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
            1 -> { //Kelvin
                when(tmp2){
                    1 -> (temp).toString()                                  //Kelvin para Kelvin
                    2 -> (((temp!! - 273.15)* 9 / 5) + 32).toString()       //Kelvin para Fahrenheit
                    3 -> ((temp!! - 273.15) * 4/5).toString()               //Kelvin para Reaumur
                    4 -> (((temp!! - 273.15) * 9 / 5) + 491.67).toString()  //Kelvin para Rankine
                    5 -> (temp!! - 273.15).toString()                       //Kelvin para Celsius
                    else -> {
                        Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_SHORT).show()
                        "0"
                    }
                }
            }
            2 -> {  //Fahrenheit
                when(tmp2){
                    1 -> (((temp!! / 9 / 5) - 32) + 273.15).toString()              //Fahrenheit para Kelvin
                    2 -> (temp).toString()                                          //Fahrenheit para Fahrenheit
                    3 -> (((temp!! / 9 / 5) - 32) * 4/5).toString()                 //Fahrenheit para Reaumur
                    4 -> ((((temp!! / 9 / 5) - 32) * 9 / 5) + 491.67).toString()    //Fahrenheit para Rankine
                    5 -> ((temp!! * 9 / 5) + 32).toString()                         //Fahrenheit para Celsius
                    else -> {
                        Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_SHORT).show()
                        "0"
                    }
                }
                ((temp!! * 9 / 5) + 32).toString()
            }
            3 -> {  //Reaumur
                when(tmp2){
                    1 -> ((temp!! / 4/5) + 273.15).toString()           //Reaumur para Kelvin
                    2 -> (((temp!! / 4/5)* 9 / 5) + 32).toString()      //Reaumur para Fahrenheit
                    3 -> (temp).toString()                              //Reaumur para Reaumur
                    4 -> (((temp!! / 4/5) * 9 / 5) + 491.67).toString() //Reaumur para Rankine
                    5 -> (temp!! / 4/5).toString()                      //Reaumur para Celsius
                    else -> {
                        Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_SHORT).show()
                        "0"
                    }
                }
            }
            4 -> {  //Rankine
                when(tmp2){
                    1 -> (((temp!! / 9 / 5) - 491.67) + 273.15).toString()      //Rankine para Kelvin
                    2 -> ((((temp!! / 9 / 5) - 491.67)* 9 / 5) + 32).toString() //Rankine para Fahrenheit
                    3 -> (((temp!! / 9 / 5) - 491.67) * 4/5).toString()         //Rankine para Reaumur
                    4 -> (temp).toString()                                      //Rankine para Rankine
                    5 -> ((temp!! / 9 / 5) - 491.67).toString()                 //Rankine para Celsius
                    else -> {
                        Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_SHORT).show()
                        "0"
                    }
                }
                ((temp!! / 9 / 5) - 491.67).toString()
            }
            5 -> {  //Celsius
                when(tmp2){
                    1 -> (temp!! + 273.15).toString()           //Celsius para Kelvin
                    2 -> ((temp!! * 9 / 5) + 32).toString()     //Celsius para Fahrenheit
                    3 -> (temp!! * 4 / 5).toString()            //Celsius para Reaumur
                    4 -> ((temp!! * 9 / 5) + 491.67).toString() //Celsius para Rankine
                    5 -> (temp).toString()                      //Celsius para Celsius
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
            R.id.btn_voltar_Temperatura_Main -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
