package com.example.presidentkotlin

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_president_details.*

class PresidentDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_president_details)
        val intent = intent
        val message = intent.getStringExtra("id")
        val p = PresidentsGlobal.instance!!.presidents[message.toInt()]


        textView.text= p.name
        textView2.text = p.startYear.toString() + " - " + p.endYear.toString()
    }
}
