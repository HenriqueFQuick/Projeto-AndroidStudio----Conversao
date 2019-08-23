package com.example.conversao_temperaturas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_altura.*
import kotlinx.android.synthetic.main.activity_peso.*
import kotlinx.android.synthetic.main.activity_peso.btn_Converter_Peso
import kotlinx.android.synthetic.main.activity_peso.btn_voltar_Peso_Main
import kotlinx.android.synthetic.main.activity_peso.radioGroup_Peso1
import kotlinx.android.synthetic.main.activity_temperatura.*

class Peso : AppCompatActivity(), View.OnClickListener {

    private var tmp1 : Int = 0
    private var tmp2 : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peso)

        //verifica constantemente se o radiogroup foi alterado
        radioGroup_Peso1.setOnCheckedChangeListener { _ , checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            //calculo do peso para cada opcao apresentada
            tmp1 = when (radio.id) {
                R.id.op_lbs1 -> 1
                R.id.op_kin1 -> 2
                R.id.op_Kg1  -> 3
                else -> {
                    //Evitando erros ( improvavel de cair aqui)
                    Toast.makeText(this, "Opcao Invalida", Toast.LENGTH_SHORT).show()
                    0
                }
            }
        }

        //verifica constantemente se o radiogroup foi alterado
        radioGroup_Peso2.setOnCheckedChangeListener { _ , checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            //calculo do peso para cada opcao apresentada
            tmp2 = when (radio.id) {
                R.id.op_lbs2 -> 1
                R.id.op_kin2 -> 2
                R.id.op_Kg2  -> 3
                else -> {
                    //Evitando erros ( improvavel de cair aqui)
                    Toast.makeText(this, "Opcao Invalida", Toast.LENGTH_SHORT).show()
                    0
                }
            }
        }

        btn_Converter_Peso.setOnClickListener(this)
        btn_voltar_Peso_Main.setOnClickListener(this)
    }

    override fun onClick(v: View?){

        var temp : Double? = 0.0
        try {
            temp = edt_Peso.text.toString().toDouble()
        }catch (e: Exception){
            temp = temp ?: 0.0
        }
        var result : String = ""

        result = when (tmp1) {
            1 -> {
                when(tmp2){
                    1 -> temp.toString()
                    2 -> (temp!! / 1.322).toString()
                    3 -> (temp!! / 2.205).toString()
                    else ->{
                        "0"
                    }
                }
            }             //Convertendo para Lbs
            2 -> {
                when(tmp2){
                    1 -> (temp!! * 1.322).toString()
                    2 -> temp.toString()
                    3 -> (temp!! / 1.66666667).toString()
                    else ->{
                        "0"
                    }
                }
            }       //Convertendo para kin
            3 -> {
                (temp!! * 2.205).toString()
                when(tmp2){
                    1 -> (temp!! * 2.205).toString()
                    2 -> (temp!! * 1.66666667).toString()
                    3 -> temp.toString()
                    else ->{
                        "0"
                    }
                }
            }
            else -> {
                //Se nao foi selecionada nenhuma opcao no RadioGroup
                if(v?.id == R.id.btn_Converter_Peso) {
                    Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                    "000"
                }else "000"
            }
        }

        //verificar qual botao foi pressionado
        when(v?.id){
            //setar a resposta da conversao no resultado
            R.id.btn_Converter_Peso -> txt_Resultado_Peso.text = result

            //redirecionar para a activity Main
            R.id.btn_voltar_Peso_Main ->{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
