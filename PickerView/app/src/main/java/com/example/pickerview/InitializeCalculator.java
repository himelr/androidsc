package com.example.pickerview;


import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Created by HimelR on 18-Jan-18.
 */

class InitializeCalculator {

    private final TextView textView;
    private final Person person;
    private final NumberPicker numberPicker;
    private final NumberPicker numberPicker2;

    InitializeCalculator(TextView textView, NumberPicker numberPicker, NumberPicker numberPicker2){
        this.textView = textView;
        this.numberPicker = numberPicker;
        this.numberPicker2 = numberPicker2;
        person = new Person(170, 70);
        setValues();
    }

    private void setValues(){
        getNumberPicker().setMinValue(30);
        getNumberPicker().setMaxValue(130);
        getNumberPicker2().setMinValue(50);
        getNumberPicker2().setMaxValue(210);
        getNumberPicker().setValue(70);
        getNumberPicker2().setValue(170);
        this.textView.setText("Value:\n" + person.calculateBmi());
    }

    NumberPicker getNumberPicker() {
        return numberPicker;
    }

    NumberPicker getNumberPicker2() {
        return numberPicker2;
    }

    void calculate(int value, boolean s){
        if(s){
            person.setWeight(value);
        }
        else {
            person.setHeight(value);
        }
        this.textView.setText("Value:\n" + person.calculateBmi());


    }
}
