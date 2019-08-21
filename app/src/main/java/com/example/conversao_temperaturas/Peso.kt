package com.example.conversao_temperaturas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_peso.*
import kotlinx.android.synthetic.main.activity_peso.btn_Converter
import kotlinx.android.synthetic.main.activity_peso.btn_voltar_Temp_Main
import kotlinx.android.synthetic.main.activity_peso.radioGroup
import kotlinx.android.synthetic.main.activity_temperatura.*

class Peso : AppCompatActivity(), View.OnClickListener {

    private var tmp : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peso)

        //verifica constantemente se o radiogroup foi alterado
        radioGroup.setOnCheckedChangeListener { _ , checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            //calculo da temperatura para cada opcao apresentada
            tmp = when (radio.id) {
                R.id.op_lbs -> 1
                R.id.op_kin -> 2
                else -> {
                    //Evitando erros ( improvavel de cair aqui)
                    Toast.makeText(this, "Opcao Invalida", Toast.LENGTH_SHORT).show()
                    0
                }
            }
        }

        btn_Converter.setOnClickListener(this)
        btn_voltar_Temp_Main.setOnClickListener(this)
    }

    override fun onClick(v: View?){

        var temp : Double = edt_Peso.text.toString().toDouble()
        var result : String = ""

        result = when (tmp) {
            1 -> (temp * 2.205).toString()             //Convertendo para Kelvin
            2 -> (temp * 1.66666667).toString()       //Convertendo para Fahrenheit
            else -> {
                //Se nao foi selecionada nenhuma opcao no RadioGroup
                if(v?.id == R.id.btn_Converter) {
                    Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                    "000"
                }else "000"
            }
        }

        when(v?.id){
            R.id.btn_Converter -> txt_Resultado_Peso.text = result
            R.id.btn_voltar_Temp_Main ->{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
