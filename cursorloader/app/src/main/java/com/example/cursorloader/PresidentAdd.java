package com.example.cursorloader;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class PresidentAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_president_add);
        Button b4 = findViewById(R.id.button4);


        b4.setOnClickListener(v -> {
            TextView textView = findViewById(R.id.editText);
            TextView textView2 = findViewById(R.id.editText2);
            TextView textView3 = findViewById(R.id.editText3);
            String name = textView.getText().toString();
            int start = Integer.parseInt(textView2.getText().toString());
            int end = Integer.parseInt(textView3.getText().toString());
            try {
                President p = new President(name,start,end,"empty");
                Thread thread = new Thread(new TaskB2(this.getApplicationContext(),p));
                thread.start();
                thread.join();
                super.finish();


            }
           catch (Exception e){
               System.out.println(e.getStackTrace());

           }
        });

    }
    private class TaskB2 implements Runnable{
        private Context context;
        private President president;

        private TaskB2(Context context, President president){
            this.context = context;
            this.president = president;
        }

        @Override
        public void run() {
            AppDatabase db =  AppDatabase.getAppDatabase(context);
            db.presidentDao().insertAll(president);


        }
    }
}
