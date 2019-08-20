package com.example.conversao_temperaturas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var tmp : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //verifica constantemente se o radiogroup foi alterado
        radioGroup.setOnCheckedChangeListener { _ , checkedId ->

            val radio: RadioButton = findViewById(checkedId)

            //calculo da temperatura para cada opcao apresentada
            when (radio.id) {
                R.id.op_Kelvin -> tmp = 1
                R.id.op_Fahrenheit -> tmp = 2
                R.id.op_Reaumur -> tmp = 3
                R.id.op_Rankine -> tmp = 4
                else -> {
                    Toast.makeText(this, "Opcao Invalida", Toast.LENGTH_SHORT).show()
                    tmp = 0
                }
            }

        }

        //listener do botao ( redireciona para o metodo onClick)
        btn_Converter.setOnClickListener(this)

    }

    //metodo ativado quando o botao eh pressionado
    //metodo coloca o resultado da conta pela temperatura selecionada no txt_Resultado
    override fun onClick(v: View?) {

        // guarda o valor digitado no edt_Temperatura
        var temp : Double = edt_Temperatura.text.toString().toDouble()

        var result : String = ""
        when (tmp) {
            1 -> result = (temp + 273.15).toString()
            2 -> result = ((temp * 9 / 5) + 32).toString()
            3 -> result = (temp * 4 / 5).toString()
            4 -> result = ((temp * 9 / 5) + 491.67).toString()
            else -> {
                Toast.makeText(this, "Opcao Invalida", Toast.LENGTH_SHORT).show()
                result = "0"
            }
        }

        when(v?.id){

            R.id.btn_Converter -> txt_Resultado.text = result
        }
    }
}
