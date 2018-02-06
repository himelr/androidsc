package com.example.cursorloader.web;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cursorloader.R;

public class JSONDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_president_details_room);
        Intent intent = getIntent();
        String message = intent.getStringExtra("name");
        String message2 = intent.getStringExtra("time");

        TextView textView = findViewById(R.id.textView3);
        TextView textView2 = findViewById(R.id.textView4);
        textView.setText(message);
        textView2.setText(message2);
    }
}
