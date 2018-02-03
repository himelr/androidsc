package com.example.cursorloader.Room;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.cursorloader.AppDatabase;
import com.example.cursorloader.President;
import com.example.cursorloader.R;
import com.example.cursorloader.extra.ListAll;

import java.util.List;

public class PresidentDetailsRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_president_details_room);
        Intent intent = getIntent();
        String message = intent.getStringExtra("id");

        TextView textView = findViewById(R.id.textView3);
        TextView textView2 = findViewById(R.id.textView4);


        int _id = Integer.parseInt(message);
        Log.d("test2", "ID: (" + _id+ ")");


        AccessPre accessPre = new AccessPre(this,_id);
        Thread thread = new Thread(accessPre);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

     /*   President qp = db.presidentDao().findByid((int) _id);*/
        President qp = accessPre.getPresident();
        textView.setText(qp.getName());
        textView2.setText(qp.getStartYear() + " - " + qp.getEndYear());
    }


    private class AccessPre implements Runnable{
        private President president;
        private Context context;
        private int _id;

        AccessPre(Context context, int id){
            this.context = context;
            this._id = id + 1;
        }
        @Override
        public void run() {
            AppDatabase db = AppDatabase.getAppDatabase(context);
            //List<President> presidents = db.presidentDao().loadAllByIds(new int[_id]);
            president = db.presidentDao().findByid(_id);

        }

        public President getPresident() {
            return president;
        }

        public void setPresident(President president) {
            this.president = president;
        }
    }
}
