package com.example.conversao_temperaturas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_altura.*
import kotlinx.android.synthetic.main.activity_peso.*
import kotlinx.android.synthetic.main.activity_altura.btn_Converter_Altura
import kotlinx.android.synthetic.main.activity_altura.radioGroup_Altura1

class Altura : AppCompatActivity(), View.OnClickListener {

    private var tmp1 : Int = 0
    private var tmp2 : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_altura)

        radioGroup_Altura1.setOnCheckedChangeListener { _ , checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            //calculo da altura para cada opcao apresentada
            tmp1 = when (radio.id) {
                R.id.op_Foot1 -> 1
                R.id.op_cm1 -> 2
                else -> {
                    //Evitando erros ( improvavel de cair aqui)
                    Toast.makeText(this, "Opcao Invalida", Toast.LENGTH_SHORT).show()
                    0
                }
            }
        }

        radioGroup_Altura2.setOnCheckedChangeListener { _ , checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            //calculo da altura para cada opcao apresentada
            tmp2 = when (radio.id) {
                R.id.op_Foot2 -> 1
                R.id.op_cm2 -> 2
                else -> {
                    //Evitando erros ( improvavel de cair aqui)
                    Toast.makeText(this, "Opcao Invalida", Toast.LENGTH_SHORT).show()
                    0
                }
            }
        }

        btn_Converter_Altura.setOnClickListener(this)
        btn_voltar_Altura_Main.setOnClickListener(this)
    }

    override fun onClick(v: View?){

        var temp : Double? = 0.0
        try {
            temp = edt_Altura.text.toString().toDouble()
        }catch (e: Exception){
            temp = temp ?: 0.0
        }
        var result : String = ""

        result = when (tmp1) {
            1 -> {
                when(tmp2){
                    1 -> temp.toString()
                    2 -> (temp!! * 30.48).toString()
                    else -> {
                        "0"
                    }
                }
            }
            2 -> {
                when(tmp2){
                    1 -> (temp!! / 30.48).toString()
                    2 -> temp.toString()
                    else -> {
                        "0"
                    }
                }
            }
            else -> {
                //Se nao foi selecionada nenhuma opcao no RadioGroup
                if(v?.id == R.id.btn_Converter_Altura) {
                    Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                    "000"
                }else "000"
            }
        }

        //verificar qual botao foi pressionado
        when(v?.id){
            //setar a resposta da conversao no resultado
            R.id.btn_Converter_Altura -> txt_Resultado_Altura.text = result

            //redirecionar para a activity Main
            R.id.btn_voltar_Altura_Main ->{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
