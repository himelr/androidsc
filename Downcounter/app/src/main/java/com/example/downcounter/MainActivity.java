package com.example.downcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CountObserve {
    private Counter counter;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView =  findViewById(R.id.textView);
        Button b = findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    counter = new Counter();
                    counter.addObserver(MainActivity.this);
                    EditText editText = findViewById(R.id.editText);
                    counter.setSeconds(Integer.parseInt(editText.getText().toString()));
                    counter.start();
                }
            catch (NumberFormatException n){
            }
        }
        });
    }

    @Override
    public void update(int seconds) {
        textView.setText(seconds + "");
        System.out.println("Update");

    }

}

