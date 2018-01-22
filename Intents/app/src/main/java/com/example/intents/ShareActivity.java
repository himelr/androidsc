package com.example.intents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Intent intent = getIntent();
        if (intent.getType().equals("text/plain")){
            Bundle extras = getIntent().getExtras();
            TextView textView = findViewById(R.id.textView2);
            textView.setText(extras.getString(Intent.EXTRA_TEXT));

        }
    }
}
