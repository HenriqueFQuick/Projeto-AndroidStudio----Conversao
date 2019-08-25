package com.example.conversao_temperaturas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_peso.*
import kotlinx.android.synthetic.main.activity_peso.btn_Converter_Peso
import kotlinx.android.synthetic.main.activity_peso.btn_voltar_Peso_Main
import kotlinx.android.synthetic.main.activity_peso.radioGroup_Peso1

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
                R.id.op_lbs1 -> 1  //Convertendo a partir de lbs
                R.id.op_kin1 -> 2  //Convertendo a partir de kin
                R.id.op_Kg1  -> 3  //Convertendo a partir de kg
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
                R.id.op_lbs2 -> 1  //Convertendo para lbs
                R.id.op_kin2 -> 2  //Convertendo para kin
                R.id.op_Kg2  -> 3  //Convertendo para kg
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
            //Se nao foi digitado nenhum valor
            Toast.makeText(this, "Digite um valor", Toast.LENGTH_LONG).show()
            temp = temp ?: 0.0
        }
        var result : String = ""

        result = when (tmp1) {
            1 -> {  //Lbs
                when(tmp2){
                    1 -> temp.toString()             //lbs para lbs
                    2 -> (temp!! / 1.322).toString() //lbs para kin
                    3 -> (temp!! / 2.205).toString() //lbs para kg
                    else ->{
                        Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                        "0" //Se nao foi selecionada uma medida para conversao
                    }
                }
            }
            2 -> { //Kin
                when(tmp2){
                    1 -> (temp!! * 1.322).toString()      //kin para lbs
                    2 -> temp.toString()                  //kin para kin
                    3 -> (temp!! / 1.66666667).toString() //kin para kg
                    else ->{
                        Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                        "0" //Se nao foi selecionada uma medida para conversao
                    }
                }
            }
            3 -> {  //Kg
                (temp!! * 2.205).toString()
                when(tmp2){
                    1 -> (temp!! * 2.205).toString()      //Kg para lbs
                    2 -> (temp!! * 1.66666667).toString() //Kg para kin
                    3 -> temp.toString()                  //Kg para Kg
                    else ->{
                        Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                        "0" //Se nao foi selecionada uma medida para conversao
                    }
                }
            }
            else -> {
                //Se nao foi selecionada nenhuma opcao no RadioGroup1
                if(v?.id == R.id.btn_Converter_Peso) {
                    Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                    "0"
                }else "0"
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
