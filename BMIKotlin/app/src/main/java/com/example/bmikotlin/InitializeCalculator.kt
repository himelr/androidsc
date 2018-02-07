package com.example.bmikotlin

import android.widget.NumberPicker
import android.widget.TextView

/**
 * Created by HimelR on 07-Feb-18.
 */
class InitializeCalculator(private val text1:TextView, val numberp:NumberPicker, val numberp2:NumberPicker,private val person: Person ) {
    init {
        setup()
    }

    private fun setup() {
       numberp.minValue = 30
       numberp.maxValue = 130
       numberp2.minValue = 120
       numberp2.maxValue = 210
       numberp.value = 70
       numberp2.value = 170
       text1.text = ("Value:\n" + person.bmi())
    }

    fun calculate(value: Int, s: Boolean) {
        if (s) {
            person.weight = value
        } else {
            person.height = value
        }
        text1.text = ("Value:\n" + person.bmi())


    }
}