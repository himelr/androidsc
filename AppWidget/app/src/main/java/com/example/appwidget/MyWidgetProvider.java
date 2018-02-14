package com.example.appwidget;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyWidgetProvider extends AppWidgetProvider {


    private static final String[] cities = new String[5];
    private int number  = 0;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        // Get all ids
        ComponentName thisWidget = new ComponentName(context,
                MyWidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int widgetId : allWidgetIds) {
            // create some random data
            number = ThreadLocalRandom.current().nextInt(0, 4 + 1);
            citiesAdd();
            final RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.widget_layout);
            Log.w("WidgetExample", String.valueOf(number));
            // Set the text


            Thread t = new Thread(new Runnable() { public void run() {
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = jsonParser.getJSONFromUrl2("http://api.openweathermap.org/data/2.5/weather?q="+cities[number]+"&appid=b27b4c66c5ad361373ced1f8347612c4");

                try {
                    remoteViews.setTextViewText(R.id.update, parseData(jsonObject));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }});
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Register an onClickListener
            Intent intent = new Intent(context, MyWidgetProvider.class);

            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }
    public String parseData(JSONObject json) throws JSONException {

        JSONArray weather = json.getJSONArray("weather");
        JSONObject main = json.getJSONObject("main");
        JSONObject c = weather.getJSONObject(0);
        StringBuilder builder = new StringBuilder();
        builder.append("City: "+ json.getString("name") + "\n");
        builder.append("Main: "+ c.getString("main") + "\n");
        builder.append("Desc: "+ c.getString("description") + "\n");
        builder.append("Temp: "+ main.getString("temp") + "\n");


        return builder.toString();
    }
    public void citiesAdd(){

        cities[0] = "London";
        cities[1] = "Tokyo";
        cities[2] = "Dallas";
        cities[3] = "Houston";
        cities[4] = "Stockholm";

    }
}