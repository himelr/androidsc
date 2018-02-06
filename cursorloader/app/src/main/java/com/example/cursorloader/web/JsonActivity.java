package com.example.cursorloader.web;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        JSONTask jsonTask = new JSONTask();
        jsonTask.execute("http://users.metropolia.fi/~himelr/presidents.json");
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


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
