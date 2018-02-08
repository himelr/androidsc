package com.example.presidentkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presidentList3.adapter = CustomAdapter(
                applicationContext,
                R.layout.president_item,
                PresidentsGlobal.instance!!.presidents)

        presidentList3.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            Log.d("test2", "onItemClick($l)")
            val nextActivity = Intent(applicationContext, PresidentDetails::class.java)
            nextActivity.putExtra("id", l.toString() + "")
            startActivity(nextActivity)
        }
    }
}
