package com.example.pickerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final InitializeCalculator initCalc;
        initCalc = new InitializeCalculator((TextView) findViewById(R.id.textView4),(NumberPicker)findViewById(R.id.numberPicker), (NumberPicker) findViewById(R.id.numberPicker2));



        initCalc.getNumberPicker().setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                initCalc.calculate(newVal,true);

            }
        });

        initCalc.getNumberPicker2().setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                initCalc.calculate(newVal,false);

            }
        });
    }

}
