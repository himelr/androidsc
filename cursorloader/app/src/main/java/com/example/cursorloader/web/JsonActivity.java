package com.example.cursorloader.web;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cursorloader.Cursor.MainActivity;
import com.example.cursorloader.President;
import com.example.cursorloader.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonActivity extends AppCompatActivity {
    private List<President> presidentList;
    private ListView lv;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String json = bundle.getString("json");


                try {
                    JSONObject jsonObject = new JSONObject(json);
                    list(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        Intent intent = new Intent(this, JsonService.class);
        startService(intent);
        lv = findViewById(R.id.presidentList3);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("test2", "onItemClick(" + l + ")");
                President p = presidentList.get(i);
                Intent nextActivity = new Intent(getApplicationContext(), JSONDetails.class);
                nextActivity.putExtra("name", p.getName()+"");
                nextActivity.putExtra("time", p.getTime()+"");
                startActivity(nextActivity);

            }
        });

    }
    public void list(JSONObject json){
        try {
            List<President> presidentsList = new ArrayList();

            JSONArray presidents = json.getJSONArray("presidents");
            for(int i = 0; i < 8; i++){
                JSONObject c = presidents.getJSONObject(i);
                String name = c.getString("name");
                String time = c.getString("time");
                presidentsList.add(new President(name,time));

            }
            presidentList = presidentsList;
            lv.setAdapter(new ArrayAdapter<President>(getApplicationContext(),R.layout.president_item,presidentList)

            );


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(
                JsonService.NOTIFICATION));
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }




    private class JSONTask extends AsyncTask<String, String, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();
            JSONObject json = jParser.getJSONFromUrl2(args[0]);
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {

            try {
                List<President> presidentsList = new ArrayList();

                JSONArray presidents = json.getJSONArray("presidents");
                for(int i = 0; i < 8; i++){
                    JSONObject c = presidents.getJSONObject(i);
                    String name = c.getString("name");
                    String time = c.getString("time");
                    presidentsList.add(new President(name,time));

                }
                presidentList = presidentsList;
                lv.setAdapter(new ArrayAdapter<President>(getApplicationContext(),R.layout.president_item,presidentList)

                );
                Context context = getApplicationContext();
                CharSequence text = "Download Complete!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
