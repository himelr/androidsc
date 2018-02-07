package com.example.bmikotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val initCalc: InitializeCalculator
        val person = Person(150,70)
        initCalc = InitializeCalculator(textView4, numberPicker,numberPicker2,person)
        initCalc.numberp.setOnValueChangedListener({ picker, oldVal, newVal -> initCalc.calculate(newVal, true) })
        initCalc.numberp2.setOnValueChangedListener({ picker, oldVal, newVal -> initCalc.calculate(newVal, false) })

    }
}
