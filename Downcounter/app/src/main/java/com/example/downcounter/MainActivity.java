package com.example.downcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Counter counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);
        counter = new Counter(textView);
        Button b = findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText editText = findViewById(R.id.editText);
                    counter.setSeconds(Integer.parseInt(editText.getText().toString()));
                    Thread thread = new Thread(counter);
                    thread.start();
                }
            catch (NumberFormatException n){

            }

        }
        });

    }
}
