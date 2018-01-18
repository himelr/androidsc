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
        NumberPicker numberPicker = findViewById(R.id.numberPicker);
        NumberPicker numberPicker2 = findViewById(R.id.numberPicker2);
        final TextView textView = findViewById(R.id.textView4);
        final Person person = new Person(170, 70);

        textView.setText("Value:\n" + person.calculateBmi());
        numberPicker.setMinValue(30);
        numberPicker.setMaxValue(130);
        numberPicker2.setMinValue(50);
        numberPicker2.setMaxValue(210);
        numberPicker.setValue(70);
        numberPicker2.setValue(170);


        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                person.setWeight(newVal);
                textView.setText("Value:\n" + person.calculateBmi());
            }
        });

        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                person.setHeight(newVal);
                textView.setText("Value:\n" + person.calculateBmi());
            }
        });
    }

}
