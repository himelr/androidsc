package com.example.tablelayout;


import android.widget.TextView;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * Created by HimelR on 18-Jan-18.
 */

class Calculator  {
    private String calculation;
    private TextView textView,textView2;
    private final String numbers = "1234567890";
    private Queue<Integer> q;
    private Stack<String> op;
    private int value = 0;




 Calculator(TextView textView, TextView textView2){
    calculation = "";
    this.textView = textView;
    this.textView2 = textView2;
    q = new LinkedList<>();
    op = new Stack<>();

 }


    public String getCalculation() {
        return calculation;
    }


    public void addCharacter(Character c){

     final String operators = "+-/*";

     if(!numbers.contains(String.valueOf(c))) {

         try {
             String last = calculation.substring(calculation.length() - 1);
             if (!operators.contains(last)) {
                 calculation += c;
                 textView.setText(calculation);

             }
         } catch (StringIndexOutOfBoundsException e) {
             textView2.setText("Error");

         }

     }
     else {
         calculation += c;
         textView.setText(calculation);
     }
    }
    public void calculate()  {

        if (!calculation.isEmpty()) {

            String f = String.valueOf(calculation.charAt(0));

            if (numbers.contains(f)) {
                if (op.isEmpty()){
                    q.add(Integer.parseInt(f));
                    calculation = calculation.substring(1);

                }
                else if (value == 0){
                    String qnumbs = "";
                    while(!q.isEmpty()){
                        qnumbs+=q.remove();
                    }
                    value+=Integer.parseInt(qnumbs);
                }

                else {
                    q.add(Integer.parseInt(f));
                    calculation = calculation.substring(1);
                }
            }

            else {

                if(op.isEmpty()){

                    op.push(f);
                    calculation = calculation.substring(1);
                    addToVal();

                }

                else{
                    String ope = op.pop();
                    op.push(f);
                    calculation = calculation.substring(1);
                    operate(ope.charAt(0));

                }

            }
            calculate();
        }
    }
    void addToVal(){
        System.out.println("addto val");
        if(value == 0){
            String qnumbs = "";

            while(!q.isEmpty()){
                qnumbs+=q.remove();
            }
            value = Integer.parseInt(qnumbs);

        }
    }
    void end(){


            try {
                String op = this.op.pop();
                System.out.println(op.charAt(0) + "op");
                operate(op.charAt(0));
                this.textView2.setText(value + "");

            }
            catch (NumberFormatException e){
                this.textView2.setText("Empty string");
            }
            catch (EmptyStackException e){
                if(!q.isEmpty()) {
                    operate('+');
                    this.textView2.setText(value + "");
                }
            }


    }
    void operate(Character c){

        String qnumbs = "";
        while(!q.isEmpty()){
            qnumbs+=q.remove();
        }
        switch (c){

            case '+':
                value += Integer.parseInt(qnumbs);
                break;
            case '-':
                value -=Integer.parseInt(qnumbs);
                break;
            case '*':
                value *=Integer.parseInt(qnumbs);
                break;
            case '/':
                value /=Integer.parseInt(qnumbs);
                break;

        }


    }
    void reset(){
        value = 0;
        calculation = "";
        q = new LinkedList<>();
        op = new Stack<>();
        this.textView.setText("");


    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
