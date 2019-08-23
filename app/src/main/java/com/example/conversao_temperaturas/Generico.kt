package com.example.conversao_temperaturas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_altura.*
import kotlinx.android.synthetic.main.activity_distancia.*
import kotlinx.android.synthetic.main.activity_generico.*

class Generico : AppCompatActivity(), View.OnClickListener {
    private var tmp : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generico)

        //verifica constantemente se o radiogroup foi alterado
        radioGroup_Generico.setOnCheckedChangeListener { _ , checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            //calculo da medida para cada opcao apresentada
            tmp = when (radio.id) {
                R.id.op_K_ -> 1
                R.id.op_H_ -> 2
                R.id.op_DA_ -> 3
                R.id.op_D_ -> 4
                R.id.op_C_ -> 5
                R.id.op_M_ -> 6

                else -> {
                    //Evitando erros ( improvavel de cair aqui)
                    Toast.makeText(this, "Opcao Invalida", Toast.LENGTH_SHORT).show()
                    0
                }
            }
        }

        btn_Converter_Generico.setOnClickListener(this)
        btn_voltar_Generico_Main.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        var temp : Double? = 0.0
        try {
            temp = edt_Altura.text.toString().toDouble()
        }catch (e: Exception){
            temp = temp ?: 0.0
        }
        var result : String = ""

        result = when (tmp) {
            1 -> (temp!! * 1000).toString()             //Convertendo para K_
            2-> (temp!! * 100).toString()             //Convertendo para H_
            3-> (temp!! * 10).toString()             //Convertendo para DA_
            4 -> (temp!! / 10).toString()             //Convertendo para D_
            5-> (temp!! / 100).toString()             //Convertendo para C_
            6 -> (temp!! / 1000).toString()             //Convertendo para M_
            else -> {
                //Se nao foi selecionada nenhuma opcao no RadioGroup
                if(v?.id == R.id.btn_Converter_Generico) {
                    Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                    "000"
                }else "000"
            }
        }

        //verificar qual botao foi pressionado
        when(v?.id){
            //setar a resposta da conversao no resultado
            R.id.btn_Converter_Generico -> txt_Resultado_Distancia.text = result

            //redirecionar para a activity Main
            R.id.btn_voltar_Generico_Main ->{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
