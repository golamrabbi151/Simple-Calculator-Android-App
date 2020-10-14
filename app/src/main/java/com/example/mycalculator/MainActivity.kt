package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){
        tvInput.append((view as Button).text)
        lastNumeric=true
    }

    fun onClear(view: View){
        tvInput.text=""
        lastNumeric=false
        lastDot=false
    }

    fun onDecimalPoint(view: View){
        if (lastNumeric && !lastDot){
            tvInput.append(".")
            lastDot=true
            lastNumeric=false
        }
    }



    fun onOperator(view: View){
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric=false
            lastDot=false
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")){
            return false
        }else{
            value.contains("+") || value.contains("-") || value.contains("*") || value.contains("/")
        }
    }



    fun onEqual(view: View){
        if (lastNumeric){
           var tvValue = tvInput.text.toString();
            var prefix = ""
            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                when {
                    tvValue.contains("-") -> {
                        var setSplit = tvValue.split("-")
                        var one = setSplit[0]
                        var two = setSplit[1]
                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }
                        tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                    }
                    tvValue.contains("+") -> {
                        var setSplit = tvValue.split("+")
                        var one = setSplit[0]
                        var two = setSplit[1]
                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }
                        tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                    }
                    tvValue.contains("*") -> {
                        var setSplit = tvValue.split("*")
                        var one = setSplit[0]
                        var two = setSplit[1]
                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }
                        tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                    }
                    tvValue.contains("/") -> {
                        var setSplit = tvValue.split("/")
                        var one = setSplit[0]
                        var two = setSplit[1]
                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }
                        tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                    }
                }

                /*
                *
                *
                if(tvValue.contains("-")){
                    var setSplit = tvValue.split("-")
                    var one = setSplit[0]
                    var two = setSplit[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = (one.toDouble() - two.toDouble()).toString()
                }else if (tvValue.contains("+")){
                    var setSplit = tvValue.split("+")
                    var one = setSplit[0]
                    var two = setSplit[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = (one.toDouble() + two.toDouble()).toString()
                }else if (tvValue.contains("*")){
                    var setSplit = tvValue.split("*")
                    var one = setSplit[0]
                    var two = setSplit[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = (one.toDouble() * two.toDouble()).toString()
                }else if (tvValue.contains("/")){
                    var setSplit = tvValue.split("/")
                    var one = setSplit[0]
                    var two = setSplit[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = (one.toDouble() / two.toDouble()).toString()
                }
                *
                * */

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun removeZeroAfterDot(result: String): String {
        var value = result
        if (value.contains(".0"))
            value = value.substring(0,value.length -2)
            return value


    }

}