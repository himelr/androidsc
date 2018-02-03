package com.example.cursorloader.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.cursorloader.AppDatabase;
import com.example.cursorloader.President;
import com.example.cursorloader.R;

public class PresidentUpdate extends AppCompatActivity {
    private President president;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_president_update);
        Intent intent = getIntent();
        String message = intent.getStringExtra("id");
        int id = Integer.parseInt(message);

        Log.d("test2", id + "iddd");
        Button b4 = findViewById(R.id.button5);
        TextView textView = findViewById(R.id.editText5);
        TextView textView2 = findViewById(R.id.editText6);
        TextView textView3 = findViewById(R.id.editText7);

        Thread q = new Thread(new AccessPre(this.getApplicationContext(),id));
        q.start();
        try {
            q.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        textView.setText(president.getName());
        textView2.setText(president.getStartYear() + "");
        textView3.setText(president.getEndYear() + "");


        b4.setOnClickListener(v -> {

            String name = textView.getText().toString();
            int start = Integer.parseInt(textView2.getText().toString());
            int end = Integer.parseInt(textView3.getText().toString());
            try {
                President p = new President(name,start,end,"empty");
                Thread thread = new Thread(new TaskB3(this.getApplicationContext(),p,id));
                thread.start();
                thread.join();
                super.finish();


            }
            catch (Exception e){
                System.out.println(e.getStackTrace());

            }
        });

    }
    private class TaskB3 implements Runnable{
        private Context context;
        private President president;
        private int id;

        private TaskB3(Context context, President president,int id){
            this.context = context;
            this.president = president;
            this.id = id;
        }

        @Override
        public void run() {
            AppDatabase db =  AppDatabase.getAppDatabase(context);
            president.setId(id);
            db.presidentDao().update(president);


        }
    }
    private class AccessPre implements Runnable{

        private Context context;
        private int _id;

        AccessPre(Context context, int id){
            this.context = context;
            this._id = id;
        }
        @Override
        public void run() {
            AppDatabase db = AppDatabase.getAppDatabase(context);
            president = db.presidentDao().findByid(_id);

        }
    }
}

