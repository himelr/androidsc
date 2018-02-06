package com.example.cursorloader;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.cursorloader.Cursor.MainActivity;
import com.example.cursorloader.Room.RoomActivity;
import com.example.cursorloader.ViewModel.ViewModelActivity;
import com.example.cursorloader.web.JsonActivity;

public class Main2Activity extends AppCompatActivity {
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button b1 = findViewById(R.id.button);
        Button b2 = findViewById(R.id.button2);
        Button b3 = findViewById(R.id.button3);
        Button b4 = findViewById(R.id.button4);

        b1.setOnClickListener(v -> {
            Intent intent = new Intent(context, RoomActivity.class);
            startActivity(intent);
        });

        b2.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);

        });
        b3.setOnClickListener(v -> {
            Intent intent = new Intent(context, ViewModelActivity.class);
            startActivity(intent);

        });
        b4.setOnClickListener(v -> {
            Intent intent = new Intent(context, JsonActivity.class);
            startActivity(intent);
            });

    }

}
