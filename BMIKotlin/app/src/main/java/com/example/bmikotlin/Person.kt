package com.example.bmikotlin




/**
 * Created by HimelR on 07-Feb-18.
 */
class Person (var height:Int,var weight: Int){

    fun bmi() : Double{
        val heightD:Double = height.toDouble() / 100.toDouble()

        return round(weight / Math.pow(heightD, 2.0),2)
    }

    private fun round(value: Double, precision: Int): Double {
        val scale = Math.pow(10.0, precision.toDouble()).toInt()
        return Math.round(value * scale).toDouble() / scale
    }
}