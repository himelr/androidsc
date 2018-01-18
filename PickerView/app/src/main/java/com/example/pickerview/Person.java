package com.example.pickerview;

/**
 * Created by HimelR on 18-Jan-18.
 */

class Person {

    private int height;
    private int weight;

    Person(int height, int weight){

        this.setHeight(height);
        this.setWeight(weight);

    }
    double calculateBmi(){
        double heightD = (double) height / (double) 100;

        return round(weight / Math.pow(heightD,2.0), 1);

    }


    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    void setHeight(int height) {
        this.height = height;
    }

    void setWeight(int weight) {
        this.weight = weight;
    }
}
