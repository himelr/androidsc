package com.example.tablelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private  Button btn_one, btn_two, btn_three, btn_four, btn_five, btn_six, btn_seven, btn_eight, btn_nine, btn_zero, btn_plus, btn_minus, btn_times, btn_divide, btn_equals, btn_clear;

    private TextView text,text2,text3;
    private Calculator calc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setEles();



        text = findViewById(R.id.et_result);
        text2 = findViewById(R.id.et_result2);
        text3 = findViewById(R.id.expression);
        calc = new Calculator(text,text2);


    }
    @Override
    public void onClick(View button) {
        switch (button.getId()) {
            case R.id.btn_one:
                calc.addCharacter('1');
                break;
            case R.id.btn_two:
                calc.addCharacter('2');
                break;
            case R.id.btn_three:
                calc.addCharacter('3');
                break;
            case R.id.btn_four:
                calc.addCharacter('4');
                break;
            case R.id.btn_five:
                calc.addCharacter('5');
                break;
            case R.id.btn_six:
                calc.addCharacter('6');
                break;
            case R.id.btn_seven:
                calc.addCharacter('7');
                break;
            case R.id.btn_eight:
                calc.addCharacter('8');
                break;
            case R.id.btn_nine:
                calc.addCharacter('9');
                break;
            case R.id.btn_zero:
                calc.addCharacter('0');
                break;
            case R.id.btn_plus:
                calc.addCharacter('+');
                break;
            case R.id.btn_minus:
                calc.addCharacter('-');
                break;
            case R.id.btn_times:
                calc.addCharacter('*');
                break;
            case R.id.btn_divide:
                calc.addCharacter('/');
                break;
            case R.id.btn_equals:
                text3.setText(calc.getCalculation());
                calc.calculate();
                calc.end();
                System.out.println("End:" + calc.getValue());
                calc.reset();

                break;
            case R.id.btn_clear:
                calc.reset();

                break;





        }
    }
    void setEles(){
        btn_one= (Button) findViewById(R.id.btn_one);
        btn_two= (Button) findViewById(R.id.btn_two);
        btn_three= (Button) findViewById(R.id.btn_three);
        btn_four = (Button) findViewById(R.id.btn_four);
        btn_five = (Button) findViewById(R.id.btn_five);
        btn_six= (Button) findViewById(R.id.btn_six);
        btn_seven= (Button) findViewById(R.id.btn_seven);
        btn_eight = (Button) findViewById(R.id.btn_eight);
        btn_nine= (Button) findViewById(R.id.btn_nine);
        btn_zero = (Button) findViewById(R.id.btn_zero);
        btn_plus= (Button) findViewById(R.id.btn_plus);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_times = (Button) findViewById(R.id.btn_times);
        btn_divide= (Button) findViewById(R.id.btn_divide);
        btn_equals = (Button) findViewById(R.id.btn_equals);
        btn_clear= (Button) findViewById(R.id.btn_clear);
        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);
        btn_five.setOnClickListener(this);
        btn_six.setOnClickListener(this);
        btn_seven.setOnClickListener(this);
        btn_eight.setOnClickListener(this);
        btn_nine.setOnClickListener(this);
        btn_zero.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_times.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_equals.setOnClickListener(this);
        btn_clear.setOnClickListener(this);



    }





}
