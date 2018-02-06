package com.example.downcounter;

import android.os.AsyncTask;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by HimelR on 29-Jan-18.
 */

class Counter {
    private int seconds;
    private CountDown countDown;
    private Set<CountObserve> countObserves;

Counter (){
    this.countDown = new CountDown();
    this.countObserves = new HashSet<>();


}
public void start(){
    countDown.execute(seconds);

}

public void addObserver(CountObserve countObserve){
    countObserves.add(countObserve);

}

public void notifyO(int seconds){
    for(CountObserve c : countObserves){
        c.update(seconds);
    }
}





    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public class CountDown extends AsyncTask<Integer, Integer, Long> {

        protected void onProgressUpdate(Integer... progress) {
            System.out.println(progress[0]);
            notifyO(progress[0]);
        }
        protected void onPostExecute(Long result) {

        }
        @Override
        protected Long doInBackground(Integer... integers) {

            while (seconds >= 0) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(seconds);
                seconds--;
            }
            return null;
        }
    }
}
