package com.example.conversao_temperaturas

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_altura.*
import kotlinx.android.synthetic.main.activity_peso.*
import kotlinx.android.synthetic.main.activity_peso.btn_Converter_Peso
import kotlinx.android.synthetic.main.activity_peso.btn_voltar_Peso_Main

class Peso : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private var tmp1 : String = ""
    private var tmp2 : String = ""

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peso)

        ArrayAdapter.createFromResource(this, R.array.opcoes_Peso1, android.R.layout.simple_spinner_item).also{ adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_Peso1.adapter = adapter
        }
        ArrayAdapter.createFromResource(this, R.array.opcoes_Peso2, android.R.layout.simple_spinner_item).also{ adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_Peso2.adapter = adapter
        }

        btn_Converter_Peso.setOnClickListener(this)
        btn_voltar_Peso_Main.setOnClickListener(this)
        spinner_Peso1.onItemSelectedListener = this
        spinner_Peso2.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id){
            R.id.spinner_Peso1 -> tmp1 = parent.getItemAtPosition(position).toString()
            R.id.spinner_Peso2 -> tmp2 = parent.getItemAtPosition(position).toString()
            else -> Toast.makeText(this, "Opcao Invalida", Toast.LENGTH_LONG).show()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        tmp1 = "Kg"
        tmp2 = "Lbs"
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
            "Lbs" -> {  //Lbs
                when(tmp2){
                    "Lbs"   -> temp.toString()             //lbs para lbs
                    "Kin"   -> (temp!! / 1.322).toString() //lbs para kin
                    "Kg"    -> (temp!! / 2.205).toString() //lbs para kg
                    else ->{
                        Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                        "0" //Se nao foi selecionada uma medida para conversao
                    }
                }
            }
            "Kin" -> { //Kin
                when(tmp2){
                    "Lbs"   -> (temp!! * 1.322).toString()      //kin para lbs
                    "Kin"   -> temp.toString()                  //kin para kin
                    "Kg"    -> (temp!! / 1.66666667).toString() //kin para kg
                    else ->{
                        Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                        "0" //Se nao foi selecionada uma medida para conversao
                    }
                }
            }
            "Kg" -> {  //Kg
                when(tmp2){
                    "Lbs"   -> (temp!! * 2.205).toString()      //Kg para lbs
                    "Kin"   -> (temp!! * 1.66666667).toString() //Kg para kin
                    "Kg"    -> temp.toString()                  //Kg para Kg
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
            R.id.btn_voltar_Peso_Main -> finish()
        }
    }
}
