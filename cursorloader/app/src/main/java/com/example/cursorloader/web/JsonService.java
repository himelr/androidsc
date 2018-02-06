package com.example.cursorloader.web;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.cursorloader.President;
import com.example.cursorloader.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HimelR on 06-Feb-18.
 */

public class JsonService extends IntentService {
    public static final String URL = "http://users.metropolia.fi/~himelr/presidents.json";
    public static final String NOTIFICATION = "com.example.cursorloader.web";

    public JsonService() {
        super("JsonService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        JSONParser jParser = new JSONParser();
        JSONObject json = jParser.getJSONFromUrl2(URL);
        publishResults(json);


    }
    private void publishResults(JSONObject json) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra("json", json.toString());
        notifier();
        sendBroadcast(intent);
    }
    public void notifier(){

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String id = "my_channel_01";

        CharSequence name = getString(R.string.app_name);

        String description = getString(R.string.description);
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(id, name, importance);
        mChannel.setDescription(description);
        mChannel.enableLights(true);
        mChannel.setLightColor(Color.RED);
        mChannel.enableVibration(true);
        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        mNotificationManager.createNotificationChannel(mChannel);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, id)
                        .setSmallIcon(R.drawable.w24)
                        .setContentTitle("Web")
                        .setContentText("Download complete");
        Intent resultIntent = new Intent(this, JsonActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(JsonActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);

        mNotificationManager.notify(3, mBuilder.build());

    }
}
