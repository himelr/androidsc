package com.example.adaptertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class PresidentDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_president_details);
        Intent intent = getIntent();
        String message = intent.getStringExtra("presidentName");
        String message2 = intent.getStringExtra("startYear");
        String message3 = intent.getStringExtra("endYear");
        Log.d("test2", message2);

        // Capture the layout's TextView and set the string as its text

        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        textView.setText(message);
        textView2.setText(message2+ "\n" + "-\n" + message3);
    }
}
