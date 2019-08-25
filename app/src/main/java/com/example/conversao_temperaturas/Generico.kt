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
import kotlinx.android.synthetic.main.activity_temperatura.*

class Generico : AppCompatActivity(), View.OnClickListener {

    private var tmp1 : Int = 0
    private var tmp2 : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generico)

        //verifica constantemente se o radiogroup foi alterado
        radioGroup_Generico1.setOnCheckedChangeListener { _ , checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            //calculo da medida para cada opcao apresentada
            tmp1 = when (radio.id) {
                R.id.op_K_1-> 1   //Convertendo a partir de K_
                R.id.op_H_1 -> 2  //Convertendo a partir de H_
                R.id.op_DA_1 -> 3 //Convertendo a partir de DA_
                R.id.op__1 -> 4   //Convertendo a partir de _
                R.id.op_D_1 -> 5  //Convertendo a partir de D_
                R.id.op_C_1 -> 6  //Convertendo a partir de C_
                R.id.op_M_1 -> 7  //Convertendo a partir de M_
                else -> {
                    //Evitando erros ( improvavel de cair aqui)
                    Toast.makeText(this, "Opcao Invalida", Toast.LENGTH_SHORT).show()
                    0
                }
            }
        }

        //verifica constantemente se o radiogroup foi alterado
        radioGroup_Generico2.setOnCheckedChangeListener { _ , checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            //calculo da medida para cada opcao apresentada
            tmp2 = when (radio.id) {
                R.id.op_K_2-> 1   //Convertendo para K_
                R.id.op_H_2 -> 2  //Convertendo para H_
                R.id.op_DA_2 -> 3 //Convertendo para DA_
                R.id.op__2 -> 4   //Convertendo para _
                R.id.op_D_2 -> 5  //Convertendo para D_
                R.id.op_C_2 -> 6  //Convertendo para C_
                R.id.op_M_2 -> 7  //Convertendo para M_
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

    //Metodo chamado quando algo com propriedades de click eh cicado no layout
    override fun onClick(v: View?) {

        var temp : Double? = 0.0
        try {
            temp = edt_Generico.text.toString().toDouble()
        }catch (e: Exception){
            //Se nao foi digitado nenhum valor
            Toast.makeText(this, "Digite um valor", Toast.LENGTH_LONG).show()
            temp = temp ?: 0.0
        }
        var result : String = ""

        result = when (tmp1) {
            1 -> { //K_
                when(tmp2){
                    1  -> temp.toString()               //K_ para K_
                    2  -> (temp!! / 10).toString()      //K_ para H_
                    3  -> (temp!! / 100).toString()     //K_ para DA_
                    4  -> (temp!! / 1000).toString()    //K_ para _
                    5  -> (temp!! / 10000).toString()   //K_ para D_
                    6  -> (temp!! / 100000).toString()  //K_ para C_
                    7  -> (temp!! / 1000000).toString() //K_ para M_
                    else -> {
                        Toast.makeText(this, "Marque uma opcao", Toast.LENGTH_SHORT).show()
                        "0"
                    }
                }
            }
            2-> { //H_
                when(tmp2){
                    1  -> (temp!! * 10).toString()      //H_ para K_
                    2  -> (temp).toString()             //H_ para H_
                    3  -> (temp!! / 10).toString()      //H_ para DA_
                    4  -> (temp!! / 100).toString()     //H_ para _
                    5  -> (temp!! / 1000).toString()    //H_ para D_
                    6  -> (temp!! / 10000).toString()   //H_ para C_
                    7  -> (temp!! / 100000).toString()  //H_ para M_
                    else -> {
                        Toast.makeText(this, "Marque uma opcao", Toast.LENGTH_SHORT).show()
                        "0"
                    }
                }
            }
            3-> { //DA_
                when(tmp2){
                    1  -> (temp!! * 100).toString()     //DA_ para K_
                    2  -> (temp!! * 10).toString()      //DA_ para H_
                    3  -> (temp).toString()             //DA_ para DA_
                    4  -> (temp!! / 10).toString()      //DA_ para _
                    5  -> (temp!! / 10000).toString()   //DA_ para D_
                    6  -> (temp!! / 100000).toString()  //DA_ para C_
                    7  -> (temp!! / 1000000).toString() //DA_ para M_
                    else -> {
                        Toast.makeText(this, "Marque uma opcao", Toast.LENGTH_SHORT).show()
                        "0"
                    }
                }
            }
            4 -> { //_
                when(tmp2){
                    1  -> (temp!! * 1000).toString()    //_ para K_
                    2  -> (temp!! * 100).toString()     //_ para H_
                    3  -> (temp!! * 10).toString()      //_ para DA_
                    4  -> (temp).toString()             //_ para _
                    5  -> (temp!! / 10).toString()      //_ para D_
                    6  -> (temp!! / 100).toString()     //_ para C_
                    7  -> (temp!! / 1000).toString()    //_ para M_
                    else -> {
                        Toast.makeText(this, "Marque uma opcao", Toast.LENGTH_SHORT).show()
                        "0"
                    }
                }
            }
            5-> { //D_
                when(tmp2){
                    1  -> (temp!! * 10000).toString()   //D_ para K_
                    2  -> (temp!! * 1000).toString()    //D_ para H_
                    3  -> (temp!! * 100).toString()     //D_ para DA_
                    4  -> (temp!! * 10).toString()      //D_ para _
                    5  -> (temp).toString()             //D_ para D_
                    6  -> (temp!! / 10).toString()      //D_ para C_
                    7  -> (temp!! / 100).toString()     //D_ para M_
                    else -> {
                        Toast.makeText(this, "Marque uma opcao", Toast.LENGTH_SHORT).show()
                        "0"
                    }
                }
            }
            6 -> { //C_
                when(tmp2){
                    1  -> (temp!! * 1000000).toString() //C_ para K_
                    2  -> (temp!! * 100000).toString()  //C_ para H_
                    3  -> (temp!! * 1000).toString()    //C_ para DA_
                    4  -> (temp!! * 100).toString()     //C_ para _
                    5  -> (temp!! * 10).toString()      //C_ para D_
                    6  -> (temp).toString()             //C_ para C_
                    7  -> (temp!! / 10).toString()      //C_ para M_
                    else -> {
                        Toast.makeText(this, "Marque uma opcao", Toast.LENGTH_SHORT).show()
                        "0"
                    }
                }
            }
            7 -> { // M_
                when(tmp2){
                    1  -> (temp!! * 1000000).toString() //M_ para K_
                    2  -> (temp!! * 100000).toString()  //M_ para H_
                    3  -> (temp!! * 10000).toString()   //M_ para DA_
                    4  -> (temp!! * 1000).toString()    //M_ para _
                    5  -> (temp!! * 100).toString()     //M_ para D_
                    6  -> (temp!! * 10).toString()      //M_ para C_
                    7  -> (temp).toString()             //M_ para M_
                    else -> {
                        Toast.makeText(this, "Marque uma opcao", Toast.LENGTH_SHORT).show()
                        "0"
                    }
                }
            }
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
