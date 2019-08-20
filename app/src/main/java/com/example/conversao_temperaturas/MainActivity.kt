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

    private var result : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var temp : Double = edt_Temperatura.text.toString().toDouble()


        radioGroup.setOnCheckedChangeListener { group, checkedId ->

            val radio: RadioButton = findViewById(checkedId)


            when (radio.id) {
                R.id.op_Kelvin -> {
                    //Toast.makeText(this, "$temp", Toast.LENGTH_LONG).show()
                    result = (temp + 273.15).toString()
                    //Toast.makeText(this, result, Toast.LENGTH_LONG).show()
                }
                R.id.op_Fahrenheit -> result = ((temp * 9 / 5) + 32).toString()
                R.id.op_Reaumur -> result = (temp * 4 / 5).toString()
                R.id.op_Rankine -> result = ((temp * 9 / 5) + 491.67).toString()
                else -> result = "a"
            }

        }

        btn_Converter.setOnClickListener(this)

    }

    override fun onClick(v: View?) {


        when(v?.id){

            R.id.btn_Converter -> {

                //Toast.makeText(this, result, Toast.LENGTH_LONG).show()

                txt_Resultado.text = result

            }
        }
    }
}
