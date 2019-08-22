package com.example.conversao_temperaturas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_temperatura.*

class Temperatura : AppCompatActivity(), View.OnClickListener {

    private var tmp : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperatura)

        //verifica constantemente se o radiogroup foi alterado
        radioGroup_Temperatura.setOnCheckedChangeListener { _ , checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            //calculo da temperatura para cada opcao apresentada
            tmp = when (radio.id) {
                R.id.op_Kelvin -> 1
                R.id.op_Fahrenheit -> 2
                R.id.op_Reaumur -> 3
                R.id.op_Rankine -> 4
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
        // guarda o valor digitado no edt_Temperatura
        var temp : Double = edt_Temperatura.text.toString().toDouble()
        var result : String = ""

        result = when (tmp) {
            1 -> (temp + 273.15).toString()             //Convertendo para Kelvin
            2 -> ((temp * 9 / 5) + 32).toString()       //Convertendo para Fahrenheit
            3 -> (temp * 4 / 5).toString()              //Convertendo para Reaumur
            4 -> ((temp * 9 / 5) + 491.67).toString()   //Convertendo para Rankine
            else -> {
                //Se nao foi selecionada nenhuma opcao no RadioGroup
                if(v?.id == R.id.btn_Converter_Temperatura) {
                    Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                    "000"
                }else "000"
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
