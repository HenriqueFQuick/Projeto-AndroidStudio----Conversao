package com.example.conversao_temperaturas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_altura.*

class Altura : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private var opcao1 : String = ""
    private var opcao2 : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_altura)

        ArrayAdapter.createFromResource(this, R.array.opcoes_Altura1, android.R.layout.simple_spinner_item).also{adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_Altura1.adapter = adapter
        }
        ArrayAdapter.createFromResource(this, R.array.opcoes_Altura2, android.R.layout.simple_spinner_item).also{adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_Altura2.adapter = adapter
        }
        btn_Converter_Altura.setOnClickListener(this)
        btn_voltar_Altura_Main.setOnClickListener(this)
        spinner_Altura1.onItemSelectedListener = this
        spinner_Altura2.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id) {
            R.id.spinner_Altura1 -> opcao1 = parent?.getItemAtPosition(position).toString()
            R.id.spinner_Altura2 -> opcao2 = parent?.getItemAtPosition(position).toString()
            else -> Toast.makeText(this, "Opcao Invalida", Toast.LENGTH_LONG).show()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        opcao1 = "Cm"
        opcao2 = "Foot"
    }

    override fun onClick(v: View?){

        var temp : Double? = 0.0
        try {
            temp = edt_Altura.text.toString().toDouble()
        }catch (e: Exception){
            //Se nao foi digitado nenhum valor
            Toast.makeText(this, "Digite um valor", Toast.LENGTH_LONG).show()
            temp = temp ?: 0.0
        }
        var result : String = ""

        result = when (opcao1) {
            "Cm" -> { //Fcm
                when(opcao2){
                    "Cm"    -> temp.toString()             //cm para cm
                    "Foot"  -> (temp!! / 30.48).toString() //cm para foot
                    else -> {
                        Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                        "0" //Se nao foi selecionada uma medida para conversao
                    }
                }
            }
            "Foot" -> { //foot
                when(opcao2){
                    "Cm"    -> (temp!! * 30.48).toString() //foot para cm
                    "Foot"  -> temp.toString()             //foot para foot
                    else -> {
                        Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                        "0" //Se nao foi selecionada uma medida para conversao
                    }
                }
            }
            else -> {
                //Se nao foi selecionada nenhuma opcao no RadioGroup
                if(v?.id == R.id.btn_Converter_Altura) {
                    Toast.makeText(this, "Selecione uma opcao", Toast.LENGTH_LONG).show()
                    "0"
                }else "0"
            }
        }

        //verificar qual botao foi pressionado
        when(v?.id){
            //setar a resposta da conversao no resultado
            R.id.btn_Converter_Altura -> txt_Resultado_Altura.text = result

            //redirecionar para a activity Main
            R.id.btn_voltar_Altura_Main ->{
                //val intent = Intent(this, MainActivity::class.java)
                //startActivity(intent)
                finish()
            }
        }
    }
}
