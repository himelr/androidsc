package com.example.downcounter;

import android.widget.TextView;

import java.util.concurrent.TimeUnit;

/**
 * Created by HimelR on 29-Jan-18.
 */

class Counter implements Runnable {
    private int seconds;
    private TextView textView;

Counter (TextView textView){
    this.textView = textView;
}
    @Override
    public synchronized void run() {
        while (seconds >= 0) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            textView.setText(seconds + "");
            seconds--;

        }


    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
